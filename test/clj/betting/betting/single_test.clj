(ns betting.betting.single-test
  (:require [clojure.test :refer [deftest testing is are]]
            [betting.betting.single :refer [win-bet?] :as single]
            [betting.race]
            )
  )
(deftest bets
  (testing "win-bet"
    (let [results
          [{:racer/id "asdf"}
           {:racer/id "qwer"}
           {:racer/id "zxcv"}
           ]]
      (is (win-bet?
            {::single/options
             [{::single/place #{0} ::single/racer #{"asdf"}}]}
            results)
          "Win first")
      (is (win-bet?
            {::single/options
             [{::single/place #{0} ::single/racer #{"asdf"}}
              {::single/place #{1} ::single/racer #{"qwer"}}]}
            results)
          "Win first and second place")
      (is (not (win-bet?
                 {::single/options
                  [{::single/place #{0} ::single/racer #{"asdf"}}
                   {::single/place #{1} ::single/racer #{"zxcv"}}]}
                 results))
          "Win first, but lose because second place was incorrect")
      (is (win-bet?
            {::single/method :or
             ::single/options
             [{::single/place #{0} ::single/racer #{"asdf"}}
              {::single/place #{1} ::single/racer #{"zxcv"}}]}
            results)
          "Win first, lose second, but win overall because is an :or")
      ; Lose first place bet
      (is (not (win-bet?
                 {::single/options
                  [{::single/place #{0} ::single/racer #{"qwer"}}]}
                 results))
          "Lose first place bet"
          ))))
