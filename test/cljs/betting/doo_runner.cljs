(ns betting.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [betting.core-test]))

(doo-tests 'betting.core-test)

