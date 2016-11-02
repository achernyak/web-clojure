(ns db-examples.hugsql
  (:require [db-examples.core :refer [db]]
            [clojure.java.jdbc :as sql]
            [hugsql.core :as hugsql]))

(hugsql/def-db-fns "users.sql")

(add-user! db {:id "foo" :pass "bar"})

(add-user-returning! db {:id "baz" :pass "bar"})

(add-users! db {:users
                [["bob" "Bob"]
                 ["alice" "Alice"]]})

(find-user db {:id "bob"})

(find-users db {:ids ["foo" "bar" "baz"]})

(defn add-user-transaction [user]
  (sql/with-db-transaction [t-conn db]
    (if-not (find-user t-conn {:id (:id user)})
      (add-user! t-conn user))))

(add-user-transaction {:id "foobar"
                       :pass "I'm transactional"})
