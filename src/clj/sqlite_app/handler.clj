(ns sqlite-app.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [sqlite-app.layout :refer [error-page]]
            [sqlite-app.routes.home :refer [home-routes]]
            [sqlite-app.routes.tables :refer [table-routes]]
            [compojure.route :as route]
            [sqlite-app.env :refer [defaults]]
            [mount.core :as mount]
            [sqlite-app.middleware :as middleware]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      (-> #'home-routes
          (wrap-routes middleware/wrap-csrf)
          (wrap-routes middleware/wrap-formats))
      (-> #'table-routes
          ;(wrap-routes middleware/wrap-csrf)
          (wrap-routes middleware/wrap-formats))
      (route/not-found
        (:body
          (error-page {:status 404
                       :title "page not found"}))))))
