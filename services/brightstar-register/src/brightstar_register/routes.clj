(ns brightstar-register.routes
  (:require [ring.util.response :refer []]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [files not-found]]
            [brightstar-register.handlers :refer :all]))

(defroutes brightstar-routes
  (GET "/" [] index)
  (POST "/register" [] register)
  (files "/static")
  (not-found four-o-four))


