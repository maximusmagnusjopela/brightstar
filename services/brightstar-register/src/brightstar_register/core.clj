(ns brightstar-register.core
  (:require [org.httpkit.server :refer [run-server]]
            [brightstar-register.routes :refer [brightstar-routes]])
  (:gen-class))

(defn -main
  "brightstar registration service"
  [& args]
  (run-server brightstar-routes {:port 8080}))
