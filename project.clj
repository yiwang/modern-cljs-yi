(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;; CLJ AND CLJS 
  :source-paths ["src/clj" "src/cljs"]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.13"]]
  :ring {:handler modern-cljs.core/handler}

  :cljsbuild {:builds
              {:dev
               {;; clojurescript source code path
                :source-paths ["src/brepl", "src/cljs"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file
                           :output-to "resources/public/js/modern_dbg.js"

                           ;; minimum optimization
                           :optimizations :whitespace

                           ;; prettyfying emitted JS
                           :pretty-print true}}

               :prod
               {;; clojurescript source code path
                :source-paths ["src/cljs"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file
                           :output-to "resources/public/js/modern.js"

                           :optimizations :advanced

                           :pretty-print false}}
               :pre-prod
               {;; clojurescript source code path
                :source-paths ["src/brepl", "src/cljs"]

                :compiler {;; different output name
                           :output-to "resources/public/js/modern_pre.js"

                           ;; simple optimization
                           :optimizations :simple

                           ;; no need prettyfication
                           :pretty-print false}}}}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [compojure "1.2.1"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.3.0"]
                 ])
