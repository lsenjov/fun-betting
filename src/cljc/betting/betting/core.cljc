(ns betting.betting.core
  "Implements Parimutuel betting to calculate odds.
  However, due to being designed with small numbers of players in mind,
  calculates all possible pools as one.
  
  This works by calculating each permutation and each time a bet would win (multiplied by current investment),
  and inverts it. Takes a house percentage if set."
  (:require [clojure.spec.alpha :as s]
            
            [betting.betting.single :as single]
            )
  )

; Bet values are always integers, and are set to the smallest unit of currency
(s/def ::value integer?)
; Usually UUIDs, but any unique string
(s/def ::id string?)
; Switch on what type of bet, then each bet has its own smaller options
(s/def ::type keyword?)

(s/def ::bet (s/keys :req [::value ::id ::type ::single/bet]))

(s/def ::bets (s/map-of ::single/bet (s/coll-of ::bet)))
