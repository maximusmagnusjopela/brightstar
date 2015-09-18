(ns brightstar-register.core
  (:require [org.httpkit.server :refer [run-server]]
            [brightstar-register.routes :refer [brightstar-routes]]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-opts
  [["-h" "--help" "prints this usage summary and quit"]
   ["-p" "--port PORT" "listening port of the"
    :default 8080
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 65536) "port must be an integer greater then 0 and less then 65536"]]])


(defn -main
  "brightstar registration service"
  [& args]
  (let [{:keys [arguments options errors summary]} (parse-opts args cli-opts)]
    (when (options :help)
      (println summary)
      (System/exit 0))
    (when errors
      (doseq [e errors]
        (println e)
        (System/exit -1)))
    (let [{:keys [port]} options]
      (run-server brightstar-routes {:port port}))))
