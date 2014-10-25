(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;; CLJ AND CLJS 
  :source-paths ["src/clj" "src/cljs"]

  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {:builds
              [{;; CLJS source code path
                :source-paths ["src/cljs"]
                ;; Google Closure options
                :compiler {:output-to "resources/public/js/modern.js"
                           :optimizations :whitespace
                           :pretty-print true}}]}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]])
