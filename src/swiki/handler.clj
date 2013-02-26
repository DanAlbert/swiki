(ns swiki.handler
  (:use compojure.core)
  (:use [hiccup core page])
  (:use ring.middleware.reload)
  (:use ring.middleware.stacktrace)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn view-layout [title & content]
  (html5
    [:head
      [:title title]
      (include-css "/css/default.css")]
    [:body content]))
   
(defn hello-page []
  (view-layout "swiki" [:h1 "Hello, world!"]))

(defroutes app-routes
  (GET "/" [] (hello-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-reload `[swiki.handler])
      (wrap-stacktrace)))
