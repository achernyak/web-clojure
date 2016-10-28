(ns guestbook.handler
  (:require [compojure
             [core :refer [routes wrap-routes]]
             [route :as route]]
            [guestbook
             [env :refer [defaults]]
             [layout :refer [error-page]]
             [middleware :as middleware]]
            [guestbook.routes
             [home :refer [home-routes]]
             [ws :refer [websocket-routes]]]
            [mount.core :as mount]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
   #'websocket-routes
   (-> #'home-routes
       (wrap-routes middleware/wrap-csrf)
       (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
