(ns cljs-add-script.core-test
  (:require [cljs.test :refer-macros [deftest testing is use-fixtures are]]
            [cljs-react-test.utils :as tu]
            [cljs-react-test.simulate :as sim]
            [reagent.core :as reagent]
            [cljs-add-script.core :refer [add-script!]]
            [dommy.core :as dommy :refer-macros [sel1]]))

(def ^:dynamic c)

(use-fixtures :each (fn [test-fn]
                      (binding [c (tu/new-container!)]
                        (test-fn)
                        (tu/unmount! c))))

(defn body []
  [:body [:div.content]])

(deftest add-script-test
  (let [_ (reagent/render [body] c)
        script (add-script! "http://0.0.0.0"
                 (fn [e]
                   (println "test script loaded"))
                 {:parent-sel :div.content
                  :async "async"
                  :id "scr-test"})
        html-script (sel1 c :script)]
    (testing "adding script"
      (is (apply = (map #(.-outerHTML %) [script html-script]))))
    (testing "list of parameters"
      (are [result tag]
        (= result (dommy/attr script tag))
        "async" :async
        "scr-test" :id))))
