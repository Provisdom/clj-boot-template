(ns {{test-ns}}
  (:require
    [clojure.test :refer :all]
    [clojure.spec.test.alpha :as st]
    [orchestra.spec.test :as ost]
    [{{namespace}} :as {{main-ns-refer}}]))

(set! *warn-on-reflection* true)
(ost/instrument)

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))