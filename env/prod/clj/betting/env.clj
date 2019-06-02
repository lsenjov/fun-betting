(ns betting.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[betting started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[betting has shut down successfully]=-"))
   :middleware identity})
