(ns brightstar-sms.source-test
  (:require [clojure.test :refer :all]
            [brightstar-sms.source :refer :all]))

(deftest conn-str-params-test
  (testing "should return a dictionary with the hostname, port ,queue, user and password params"
    (are [in out] (= (conn-str-params in) out)
         "amqp://brightstar-rmq/sms_send?user=guest&password=guest" {:hostname "brightstar-rmq" 
                                                                     :queue "sms_send"
                                                                     :user "guest"
                                                                     :password "guest"
                                                                     :port nil}
         "amqp://brightstar-rmq:5555/sms_send?user=guest&password=guest" {:hostname "brightstar-rmq" 
                                                                          :queue "sms_send"
                                                                          :user "guest"
                                                                          :password "guest"
                                                                          :port 5555})))

