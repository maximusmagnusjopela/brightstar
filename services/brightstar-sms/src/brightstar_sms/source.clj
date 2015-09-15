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
            [clojure.data.csv :as csv]
            [clojure.core.async :as async :refer :all]
            [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]
            [langohr.basic :as lb]))

(defrecord Sms [To From Body])

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
  (let [conn (-> :conn-str options rmq/settings-from rmq/connect)
        ch (lch conn)
        qname (:queue-name options)]
    (let [aysnc-ch (async/ch)]
      (lq/declare ch qname {:durable true :auto-delete false})
      nil)))

(defmethod msg-seq :default [options]
  [])


