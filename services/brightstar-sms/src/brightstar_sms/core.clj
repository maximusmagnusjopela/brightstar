(ns brightstar-sms.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-opts
  [["-f" "--file FILE" "json file containing the messages to send"]])

(defn -main
  " Read all message from a source and sends them to the recipient via sms"
  [& args]
  (let [{:keys [arguments options errors summary] :as opts} (parse-opts args cli-opts)]
    (when errors
      (doseq [e errors]
        (println e)
        (System/exit -1)))
    (let [msg-source (source/source options)
          sender-fn (sender/sender options)]
      (loop [msg (source/read! msg-source)]
        (when msg
          (sender-fn msg)
          (recur (source/read! msg-source))))
      (println "Source has no more message to be sent")
      (System/exit 0))))


          

    
