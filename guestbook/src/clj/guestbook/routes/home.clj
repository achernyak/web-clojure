(ns guestbook.routes.home
  (:require [bouncer
             [core :as b]
             [validators :as v]]
            [compojure.core :refer [defroutes GET POST]]
            [guestbook.db.core :as db]
            [guestbook.layout :as layout]
            [ring.util.response :refer [response status]]))

(defn home-page []
  (layout/render "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/messages" [] (response (db/get-messages)))
  (GET "/about" [] (about-page)))

