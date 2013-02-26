(ns swiki.handler
  (:use compojure.core)
  (:use ring.middleware.reload)
  (:use ring.middleware.stacktrace)
  (:use swiki.middleware)
  (:use swiki.views)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (hello-page))
  (GET "/login" [] (login-page))
  (POST "/login" [username password] (login username password))
  (GET "/logout" [] (logout))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-request-logging)
      (wrap-reload `[swiki.handler])
      (wrap-stacktrace)))
