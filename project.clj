(defproject swiki "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.2"]
                 [korma "0.3.0-RC4"]
                 [sqlitejdbc "0.5.6"]
                 [digest "1.3.0"]]
  :plugins [[lein-ring "0.8.2"]]
  :ring {:handler swiki.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
