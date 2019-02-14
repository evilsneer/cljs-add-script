(defproject cljs-add-script "0.1.0-SNAPSHOT"
  :description "dynamically add script tag to selected DOM node"
  :url "https://github.com/evilsneer/cljs-add-script"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"
                  :scope "provided"]
                 [prismatic/dommy "1.1.0"]])
