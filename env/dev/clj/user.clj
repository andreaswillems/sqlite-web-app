(ns user
  (:require [luminus-migrations.core :as migrations]
            [sqlite-app.config :refer [env]]
            [mount.core :as mount]
            [sqlite-app.core :refer [start-app]]))

(defn start []
  (mount/start-without #'sqlite-app.core/repl-server))

(defn stop []
  (mount/stop-except #'sqlite-app.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


