(ns brightstar-register.views
  (:require [hiccup.core :refer [html]]
            [hiccup.form :as form]
            [brightstar-register.widgets :refer :all]
            [brightstar-register.conf :refer [conf]]))

(defn registration
  "Build the registration page"
  []
  (let [registration-form-header (-> "brightstar.conf" conf :registration-form-header)
        base-title (-> "brightstar.conf" conf :base-title)]
    (-> (registration-form registration-form-header)
        (base base-title ["css/base.css"])
        html)))
