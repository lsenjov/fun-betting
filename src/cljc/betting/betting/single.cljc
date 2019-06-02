(ns betting.betting.single
  "For bets on a single race"
  (:require [clojure.spec.alpha :as s]
            [taoensso.timbre :as log]))

(s/def ::place (s/and
                 set?
                 (s/coll-of integer?)))
(s/def ::racer (s/and
                 set?
                 (s/coll-of string?)
                 ))
; If not set, defaults to :and
(s/def ::option (s/keys :req [::place ::racer]))
(s/def ::options (s/coll-of ::option))

(s/def ::method #{:and :or})
(s/def ::bet (s/keys :req [::options]
                     :opt [::method]))


(comment
  {::method :or ; If not here, defaults to :and
   ::options
   ; In this example, the racer with id qwer-... needs to finish 0th or 1st (1st or 2nd)
   [{::place #{0 1}
     ::racer #{"qwer-asdf-zxcv"}
     }
    ; OR racer zxcv-... must finish in 8th
    {::place #{7}
     ::racer #{"zxcv-asdf-qwer"}
     }]})

(defn- win-option?
  "Does this option win with this racer and place?"
  ; Racer and place are sets
  [{::keys [racer place] :as option} [r-racer r-place :as result]]
  (log/debug "win-option? " option result)
  (and (racer r-racer)
       (place r-place)))
(defn- win-bet-single?
  "Does this bet win with this single racer/place tuple?"
  [{::keys [racer place] :as option} results]
  (log/debug "win-bet-single? " option results)
  (->> results
       (map (partial win-option? option))
       (reduce #(or %1 %2))
    ))
(defn win-bet?
  [{::keys [method options] :as bet} results]
  {:pre [(s/valid? :race/results results)]}
  (as-> results v
    (map (fn [{id :racer/id} pos] [id pos])
         v
         (range))
       ;(map (partial win-bet-single? bet))
       #(win-bet-single? % v)
       (map v options)
       (reduce
         (if (= :or method)
           #(or %1 %2)
           #(and %1 %2)
           )
         v)
       (boolean v)
       ))
