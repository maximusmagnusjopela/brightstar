(ns brightstar-sms.source)

(defprotocol Source
  "Specification of a message source"
  (read! [s] "read a message from the source (stateful operation)"))

