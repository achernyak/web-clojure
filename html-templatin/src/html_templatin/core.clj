(ns html-templatin.core
  (:require [selmer.parser :as selmer]
            [selmer.filters :as filters]
            [selmer.middleware :refer [wrap-error-page]]))

(selmer/render "Hello, {{name}}" {:name "World"})

(selmer/render-file "hello.html" {:name "World"
                                  :items (range 10)})

(filters/add-filter! :empty? empty?)

(selmer/render "{% if files|empty? %}no files{% else %}files{% endif %}"
               {:files []})

(filters/add-filter! :foo
                     (fn [x] [:safe (.toUpperCase x)]))

(selmer/render "{{x|foo}}" {:x "<div>I'm safe</div>"})

(selmer/add-tag!
 :image
 (fn [args context-map]
   (str "<img src=" (first args) "/>")))

(selmer/render "{% image \"http://foo.com/logo.jpg\" %}" {})

(defn render []
  (wrap-error-page
   (fn [template]
     {:status 200
      :body (selmer/render-file template {})})))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
