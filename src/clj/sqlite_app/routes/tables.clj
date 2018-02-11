(ns sqlite-app.routes.tables
  (:require [clojure.walk :as walk]
            [sqlite-app.layout :as layout]
            [compojure.core :refer [defroutes GET DELETE]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [sqlite-app.db.core :as db]))

(defn table-names []
  (map #(str "table_" %) (range 1 100)))

(defn overview-page []
  (doseq []
    (println (db/get-tables))
    (layout/render "tables_overview.html" {:tables (db/get-tables)})))

(defn detail-page
  ([name]  
  (layout/render "tables_detail.html" 
    { :table_name name
      :column_names (keys (walk/stringify-keys (db/get-one-entry {:table_name name})))
      :entries (map vals (db/get-all-entries {:table_name name})) }))

  ([name message]
  (do
    (println "detail-page called")
    (layout/render "tables_detail.html" 
      { :table_name name
        :message message
        :column_names (keys (walk/stringify-keys (db/get-one-entry {:table_name name})))
        :entries (map vals (db/get-all-entries {:table_name name})) }))))

(defn delete-entry!
  [request]
    (let [parameters (:params request)
          table_name (:table_name parameters)
          column_name (:column_name parameters)
          id (:id parameters)
          result (db/delete-entry! {:table_name table_name :column_name column_name :id id})]
      (when (= result 1)
        (response/ok))))        

(defroutes table-routes
  (GET "/" [] (overview-page))
  (GET "/tables/:name" [name] (detail-page name))
  (DELETE "/tables/:table_name/:id" request (delete-entry! request)))
