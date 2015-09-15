(ns brightstar-sms.sender
  (:require [twilio.core :as twilio]))

(defn twilio-sender 
  [sid auth-token]
  (fn 
    [msg] 
    (twilio/with-auth sid auth-token 
      (twilio/send-sms msg))))

;TODO: this will be a multimethod so as to be able to implement multiple sms service provider.
;for the moment it's just twilio.
(defn sender
  [options]
  (let [{:keys [sid auth-token]} options]
    (twilio-sender sid auth-token)))


 
