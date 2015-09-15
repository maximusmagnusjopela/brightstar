;      __         _       __    __       __             
;     / /_  _____(_)___ _/ /_  / /______/ /_____ ______
;    / __ \/ ___/ / __  / __ \/ __/ ___/ __/ __  / ___/
;   / /_/ / /  / / /_/ / / / / /_(__  ) /_/ /_/ / /    
;  /_.___/_/  /_/\__, /_/ /_/\__/____/\__/\__,_/_/     
;               /____/                                 
(ns brightstar-sms.source
  (:require [ring.util.codec :refer [form-decode]]
            [clojure.walk :refer [keywordize-keys]]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defrecord Sms [To From Body])

(defn conn-str-params
  "Returns a map of the necessary parameters to connect to the source encoded in conn-str"
  [conn-str]
  (let [uri (java.net.URI. conn-str)]
    (let [hostname (.getHost uri)
          queue (-> uri .getPath io/file .getName)
          port (let [p (.getPort uri)] (if (not= -1 p) p))
          params (-> uri .getQuery form-decode keywordize-keys)]
      (assoc params :hostname hostname :queue queue :port port))))
      
(defn msg-seq-dispatch
  [opts]
  (cond 
    (:file opts) :file
    (and (:conn-str opts) (.startsWith (:conn-str opts) "amqp://")) :amqp
    :else :default))

(defmulti msg-seq msg-seq-dispatch)

(defmethod msg-seq :file [options]
  (let [file (:file options)]
    (for [l (csv/read-csv (io/reader file)) 
          :let [[to from body] l]] (Sms. to from body))))

(defmethod msg-seq :amqp [options]
  nil)



(defmethod msg-seq :default [options]
  [])


