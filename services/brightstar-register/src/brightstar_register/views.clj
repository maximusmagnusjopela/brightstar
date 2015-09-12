(ns brightstar-register.views
  (:require [hiccup.core :refer [html]]
            [hiccup.form :as form]
            [brightstar-register.widgets :refer :all]))

(defn registration
  "Build the registration page"
  []
  (-> (registration-form "Welcome to brightstar")
      (base "brighstar register")
      html))

