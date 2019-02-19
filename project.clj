(defproject cljs-add-script "0.1.1"
  :description "dynamically add script tag to selected DOM node"
  :url "https://github.com/evilsneer/cljs-add-script"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"
                  :scope "provided"]
                 [prismatic/dommy "1.1.0"]]

  :profiles {:dev
             {:plugins      [[lein-doo "0.1.10"]
                             [lein-cljsbuild "1.1.6"]
                             [lein-pprint "1.2.0"]]
              :dependencies [[reagent "0.5.1" :exclusions [cljsjs/react]]
                             [cljsjs/react-with-addons "0.13.3-0"]
                             [cljs-react-test "0.1.3-SNAPSHOT"]
                             [org.clojure/core.rrb-vector "0.0.14"]]}}

  :source-paths ["src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" "test/js"]

  :cljsbuild {:builds
              {:test
               {:source-paths ["src/cljs" "test/cljs"]
                :compiler     {:output-to     "resources/public/js/compiled/test.js"
                               :main          cljs-add-script.runner
                               :optimizations :none}}}}
  :aliases {"test" ^{:doc "Clean and Test"}
                   ["do" "clean" ["with-profile" "dev" "doo" "phantom" "test" "once"]]
            "deploy!" ^{:doc "Deploy if tests succeed."}
                   ;; Nested vectors are supported for the "do" task
                   ["do" "test" ["deploy" "clojars"]]}

  :deploy-repositories [["releases"  {:sign-releases false :url "https://clojars.org/repo"}]
                        ["snapshots" {:sign-releases false :url "https://clojars.org/repo"}]]

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]
  )
