(ns brightstar-sms.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [brightstar-sms.sender :as sender]
            [brightstar-sms.source :as source])
  (:gen-class))

(def cli-opts
  [["-f" "--file FILE" "json file containing the messages to send"]
   ["-c" "--config CONF" "path to file containing the configuration"
    :default "conf.json"]
   ["-s" "--sid SID" "twilio sender ID"]
   ["-a" "--auth-token TOKEN" "twilio authentication token"]])

(defn -main
  " Read all message from a source and sends them to the recipient via sms"
  [& args]
  (let [{:keys [arguments options errors summary] :as opts} (parse-opts args cli-opts)]
    (when errors
      (doseq [e errors]
        (println e)
        (System/exit -1)))
    (let [sender-fn (sender/sender options)]
      (doseq [msg (source/msg-seq options)]
        (println (sender-fn msg))))
    (System/exit 0)))

