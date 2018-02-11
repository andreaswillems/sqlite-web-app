(ns sqlite-app.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [sqlite-app.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[sqlite-app started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[sqlite-app has shut down successfully]=-"))
   :middleware wrap-dev})
