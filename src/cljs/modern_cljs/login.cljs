(ns modern-cljs.login
  (:use [domina :only [by-id value]])
  (:require [domina.events :as ev])
  )

(defn validate-form [e]
  (let [email (value (by-id "email"))
        password (value (by-id "password"))]
    (.log js/console  email password)
    (if (and (> (count email) 0)
             (> (count password) 0))
      true
      (do
        (ev/prevent-default e)
        (js/alert "Please, complete the form!")))))

(defn ^:export init []
  (if (and js/document
           (aget js/document "getElementById"))
    (ev/listen! (by-id "submit") :click validate-form)))
