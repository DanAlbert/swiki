(ns swiki.views
  (:use compojure.core)
  (:use [hiccup core page])
  (:use digest)
  (:use swiki.db))

(defn view-layout [title & content]
  (html5
    [:head
      [:title title]
      (include-css "/css/default.css")]
    [:body content]))
   
(defn hello-page []
  (view-layout "swiki" [:h1 "Hello, world!"]))

(defn login-page []
  (view-layout "swiki - login"
    [:form {:method "post" :action "/login"}
      [:label {:for "username"}]
      [:input#username {:type "text" :name "username"}]
      [:label {:for "password"}]
      [:input#password {:type "password" :name "password"}]
      [:input {:type "submit" :value "Login"}]]))

(defn login [username password]
  (view-layout "swiki - login"
    [:p (apply format "username: %s" [username])]
    [:p (apply format "password: %s" [(digest/sha-512 password)])]))

(defn logout []
  (view-layout "swiki - login" [:p "logout page"]))
