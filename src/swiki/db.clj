(ns swiki.db
  (:use [korma core db]))

(defdb dev
  (sqlite3 {:db "swiki.db"}))

(defentity users
  (entity-fields :id :username :password))
