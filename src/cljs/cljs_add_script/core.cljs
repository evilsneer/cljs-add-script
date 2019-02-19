(ns cljs-add-script.core
  (:require [dommy.core :as dommy :refer-macros [sel sel1]]))

(defn append! [new-node container]
  (dommy/append! container new-node)
  new-node)

(def ^:private script-default-opts
  {:parent-sel :body
   :type       "text/javascript"})

(defn reducer [elem [key val]]
  (dommy/set-attr! elem key val))

(defn- set-attrs [element attrs]
  (reduce reducer element attrs))

; <script src="src" type=":type" onload="cb"></script>
(defn add-script!
  "Append a script element"
  ([src cb opts]
   (let [opts (merge script-default-opts opts)              ; normalize the options map
         parent-node (sel1 (:parent-sel opts))]             ; get the container node
     (if parent-node (->                                    ; create a script element
                       (dommy/create-element :script)
                       ; set the type attribute
                       (set-attrs (dissoc opts :parent-sel))
                       ; set src attribute
                       (dommy/set-attr! :src src)
                       ; attach the on load event
                       (dommy/listen! :load cb)
                       ; append the newly created element to the parent node
                       (append! parent-node))
                     (println "No parent node to add!"))))
  ([src cb]
   (add-script! src cb nil))
  ([src]
   (add-script! src nil)))
