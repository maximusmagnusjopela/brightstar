(ns brightstar-sms.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-opts
  [["-f" "--file FILE" "json file containing the messages to send"]])

(defn -main
  " Read all message from a source and sends them to the recipient via sms"
  [& args]
  (let [{:keys [arguments options errors summary] :as opts} (parse-opts args cli-opts)]
    (println opts)))
