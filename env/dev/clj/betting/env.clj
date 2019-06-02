(ns betting.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [betting.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[betting started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[betting has shut down successfully]=-"))
   :middleware wrap-dev})
