(ns swiki.views
  (:use compojure.core)
  (:use [hiccup core page])
  (:use [korma core db])
  (:use digest)
  (:use swiki.db)
  (:use swiki.middleware))

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

(defn register-page [& errors]
  (view-layout "swiki - register"
    [:p errors]
    [:form {:method "post" :action "/register"}
      [:label {:for "username"} "Username:"]
      [:input#username {:type "text" :name "username"}]
      [:label {:for "password"} "Password:"]
      [:input#password {:type "password" :name "password"}]
      [:label {:for "password-confirm"} "Confirm Password:"]
      [:input#password-confirm {:type "password" :name "password-confirm"}]
      [:input {:type "submit" :value "Submit"}]]))

(defn register [username password password-confirm]
  (log "password = %s, confirm = %s" password password-confirm)
  (if (= password password-confirm)
    (
      (insert users
      (values {:username username :password (digest/sha-512 password)})
      (login-page)))
    (register-page "Passwords do not match")))

(defn logout []
  (view-layout "swiki - login" [:p "logout page"]))
