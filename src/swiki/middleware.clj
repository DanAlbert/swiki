(ns swiki.middleware)

(defn- log [msg & vals]
  (let [line (apply format msg vals)]
    (locking System/out (println line))))

(defn wrap-request-logging [handler]
  (fn [{:keys [request-method uri] :as req}]
    (let [start   (System/currentTimeMillis)
          resp    (try
                    (handler req)
                    (catch Exception e
                      (let [finish (System/currentTimeMillis)
                            total (- finish start)]
                        (log "request %s %s (%dms)" request-method uri total)
                        (throw e))))
          finish  (System/currentTimeMillis)
          total   (- finish start)]
      (log "request %s %s (%dms)" request-method uri total)
      resp)))
