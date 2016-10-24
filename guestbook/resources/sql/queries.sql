-- :name save-message! :! :n
-- :doc create a new message using the name, message, and timestamp keys
insert into guestbook
(name, message, timestamp)
values (:name, :message, :timestamp)

-- :name get-messages :? :*
-- :doc selectes all available messages
select * from guestbook
