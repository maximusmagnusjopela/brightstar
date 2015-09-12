(ns brightstar-register.widgets
  (:require [hiccup.form :as form]))

(defn base
  "Returns a basic, reusable html5 template"
  [body title]
  [:html
   [:head
    [:title title]]
   [:body body]])

(defn registration-form
  [welcome-message]
  (list [:p welcome-message]
   (form/form-to [:post "register"]
     (form/text-field "Mobile Phone Number")
     (form/submit-button "Register"))))
