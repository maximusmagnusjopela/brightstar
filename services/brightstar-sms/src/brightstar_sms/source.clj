;      __         _       __    __       __             
;     / /_  _____(_)___ _/ /_  / /______/ /_____ ______
;    / __ \/ ___/ / __  / __ \/ __/ ___/ __/ __  / ___/
;   / /_/ / /  / / /_/ / / / / /_(__  ) /_/ /_/ / /    
;  /_.___/_/  /_/\__, /_/ /_/\__/____/\__/\__,_/_/     
;               /____/                                 
(ns brightstar-sms.source
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defrecord Sms [To From Body])

(defn msg-seq-dispatch
  [opts]
  (cond 
    (:file opts) :file
    :else :default))

(defmulti msg-seq msg-seq-dispatch)

(defmethod msg-seq :file [options]
  (let [file (:file options)]
    (for [l (csv/read-csv (io/reader file)) 
          :let [[to from body] l]] (Sms. to from body))))

(defmethod msg-seq :default [options]
  [])

