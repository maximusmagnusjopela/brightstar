(defproject brightstar-sms "0.1.1-alpha"
  :description "brightstar sms service"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]]
  :main ^:skip-aot brightstar-sms.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
