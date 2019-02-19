(ns cljs-add-script.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [cljs-add-script.core-test]))

(doo-tests 'cljs-add-script.core-test)