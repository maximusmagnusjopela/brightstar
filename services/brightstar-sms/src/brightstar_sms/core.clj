(ns brightstar-sms.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [brightstar-sms.sender :as sender]
            [brightstar-sms.source :as source])
  (:gen-class))

(def cli-opts
  [["-h" "--help" "prints usage summary and quit"]
   ["-f" "--file FILE" "json file containing the messages to send"]
   ["-c" "--config CONF" "path to file containing the configuration"
    :default "conf.json"]
   ["-s" "--sid SID" "twilio sender ID"]
   ["-a" "--auth-token TOKEN" "twilio authentication token"]
   ["-C" "--conn-str CONNSTR" "connection string to message source"]
   ["-q" "--queue-name QNAME" "queue name where the sms are posted (used with amqp source)"
    :default "sms_send"]])

(defn -main
  " Read all message from a source and sends them to the recipient via sms"
  [& args]
  (let [{:keys [arguments options errors summary] :as opts} (parse-opts args cli-opts)]
    (when errors
      (doseq [e errors]
        (println e)
        (System/exit -1)))
    (when (options :help)
      (println summary)
      (System/exit 0))
    (let [sender-fn (sender/sender options)]
      (doseq [msg (source/msg-seq options)]
        (sender-fn msg)))
    (System/exit 0)))

