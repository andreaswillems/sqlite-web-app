(ns sqlite-app.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[sqlite-app started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[sqlite-app has shut down successfully]=-"))
   :middleware identity})
