(ns guestbook.core)

(-> (.getelementById js/document "content")
    (.-innerHTML)
    (set! "Hello, World!"))

