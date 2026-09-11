(ns marketentry.facts "South Africa market-entry catalog.")
(def catalog
  {"ZAF" {:name "South Africa"
          :owner-authority "National Treasury / eTender Publication Portal / CSD"
          :legal-basis "PFMA; Preferential Procurement Policy Framework Act"
          :national-spec "Central Supplier Database (CSD) + eTender registration"
          :provenance "https://www.etenders.gov.za/"
          :required-evidence ["CSD registration record"
                              "CIPC company registration"
                              "SARS tax clearance record"
                              "Authorized-representative record"]
          :rep-owner-authority "National Treasury / organs of state"
          :rep-legal-basis "South African CSD registration typically required for public-sector awards"
          :rep-provenance "https://secure.csd.gov.za/"
          :corporate-number-owner-authority "CIPC / SARS"
          :corporate-number-legal-basis "Company registration number / tax reference"
          :corporate-number-provenance "https://www.cipc.co.za/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
