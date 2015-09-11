(defproject brightstar-register "0.1.1"
  :description "brightstar queue registration server"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.4.0"]]
  :main ^:skip-aot brightstar-register.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
