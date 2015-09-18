(ns brightstar-register.widgets
  (:require [hiccup.form :as form]))

(defn base
  "Returns a basic, reusable html5 template"
  ([body title styles] 
    [:html
     [:head
      [:title title]
      (for [s styles] [:link {:rel "stylesheet" :type "text/css" :href (str "/static/" s)}])
     [:body body]]])
  ([body title]
   (base body title [])))

(defn registration-form
  [welcome-message]
  (list [:p welcome-message]
   (form/form-to [:post "register"]
     (form/text-field "Mobile Phone Number")
     (form/submit-button "Register"))))
