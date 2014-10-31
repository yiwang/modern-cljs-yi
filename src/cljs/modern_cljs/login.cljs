(ns modern-cljs.login
  (:require-macros [hiccups.core :as h])
  (:use [domina :only [by-class by-id value destroy! append! prepend!]])
  (:require [domina.events :as ev])
  )

(def ^:dynamic *password-re* #"^(?=.*\d).{4,8}$")
(def ^:dynamic *email-re* #"^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$")

(defn validate-email [email]
  (destroy! (by-class "email"))
  (if (not (re-matches *email-re* (value email)))
    (do
      (prepend! (by-id "loginForm") (h/html [:div.help.email "Wrong email"]))
      false)
    true))

(defn validate-password [password]
  (destroy! (by-class "password"))
  (if (not (re-matches *password-re* (value password)))
    (do
      (append! (by-id "loginForm") (h/html [:div.help.password "Wrong password"]))
      false)
    true))

(defn validate-form [evt]
  (let [email (by-id "email")
        password (by-id "password")
        email-val (value email)
        password-val (value password)]
    (if (or (empty? email-val) (empty? password-val))
      (do
        (ev/prevent-default evt)
        (destroy! (by-class "help"))
        (append! (by-id "loginForm") (h/html [:div.help "Please complete the form"])))
      (if (and (validate-email email)
               (validate-password password))
        true
        (ev/prevent-default evt)))))

(defn ^:export init []
  (if (and js/document
           (aget js/document "getElementById"))
    (let [email (by-id "email")
          password (by-id "password")]
      (ev/listen! email :blur (fn [e] (validate-email email)))
      (ev/listen! password :blur (fn [e] (validate-password password)))
      (ev/listen! (by-id "submit") :click validate-form))))
