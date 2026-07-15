(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest zaf-has-spec-basis
  (let [sb (facts/spec-basis "ZAF")]
    (is (= 3 (count sb)))
    (is (every? #(or (str/starts-with? (:statute/url %) "https://www.justice.gov.za/")
                      (str/starts-with? (:statute/url %) "https://www.gov.za/"))
                sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["ZAF" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["zaf.labour-relations-act-66-1995"]
         (mapv :statute/id (facts/by-topic "ZAF" :labor))))
  (is (empty? (facts/by-topic "ZAF" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
