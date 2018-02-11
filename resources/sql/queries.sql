-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name update-user! :! :n
-- :doc updates an existing user record
UPDATE users
SET first_name = :first_name, last_name = :last_name, email = :email
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT * FROM users
WHERE id = :id

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE id = :id

-- :name get-tables
-- :doc retreives all table names
SELECT name FROM sqlite_master 
WHERE type='table' 
ORDER BY name;

-- :name get-all-entries :? :*
-- :doc retreives all entries in the given table
SELECT * FROM :i:table_name;

-- :name get-one-entry :? :1
-- :doc retreives one entry
SELECT * FROM :i:table_name LIMIT 1;

-- :name delete-entry! :! :n
-- :doc deletes an entry record given the id
DELETE FROM :i:table_name
WHERE :i:column_name = :id;