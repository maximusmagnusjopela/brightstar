;      __         _       __    __       __             
;     / /_  _____(_)___ _/ /_  / /______/ /_____ ______
;    / __ \/ ___/ / __  / __ \/ __/ ___/ __/ __  / ___/
;   / /_/ / /  / / /_/ / / / / /_(__  ) /_/ /_/ / /    
;  /_.___/_/  /_/\__, /_/ /_/\__/____/\__/\__,_/_/     
;               /____/                                 
(ns brightstar-sms.source
  (:require [clojure.java.io :as io]))

(defn msg-seq-dispatch
  [opts]
  (cond 
    (:file opts) :file
    :else :default))

(defmulti msg-seq msg-seq-dispatch)

(defmethod msg-seq :file [options]
  (let [file (:file options)]
    (line-seq (io/reader file))))

(defmethod msg-seq :default [options]
  [])

