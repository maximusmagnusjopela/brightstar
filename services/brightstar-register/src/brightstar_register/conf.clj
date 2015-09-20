(ns brightstar-register.conf
  (:require [cheshire.core :refer :all]
            [clojure.java.io :as io]))

(defn- conf!
  "parse the configuration file and returns a config map"
  [config-file]
  (with-open [f (io/reader config-file)]
    (parse-stream f true)))

(def conf (memoize conf!))

