(ns brightstar-register.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [brightstar-register.handlers :refer :all]))

(defroutes brightstar-routes
  (GET "/" [] index)
  (POST "/register" register)
  (not-found four-o-four))


