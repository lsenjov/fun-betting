(ns betting.race
  "Everything to do with racers and where they fit at the end"
  (:require [clojure.spec.alpha :as s])
  )

(s/def :racer/id string?)
; Note that position starts at 0 (first place)
; Adjust when displaying
(s/def :race/pos integer?)
; TODO figure out what time is to look like
(s/def :race/time any?)
; Results is by position
(s/def :race/result (s/keys :req [:racer/id] :opt [:race/time]))
(s/def :race/results
  (s/coll-of :race/result))
