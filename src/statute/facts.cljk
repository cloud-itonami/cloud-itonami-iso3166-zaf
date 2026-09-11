(ns statute.facts
  "General-law compliance catalog for South Africa (ZAF) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Companies Act 71 of 2008 and Protection of Personal Information Act 4
  of 2013 both cite justice.gov.za PDFs that rendered with fully legible
  text (the front-matter assent/commencement table and preamble render
  cleanly, unusually strong for a PDF source). The Labour Relations Act
  66 of 1995 cites a gov.za-hosted PDF whose body text is corrupted by a
  font-subsetting artifact, but the Act's own title ('Labour Relations
  Act, 1995') and its presidential-assent line ('...29 November 1995')
  both render legibly within the otherwise-garbled text -- the same
  legible-header/corrupted-body tier used for Denmark's Datatilsynet PDF
  and Berlin's IFG/BlnDSG PDFs. A law not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"ZAF"
   [{:statute/id "zaf.companies-act-71-2008"
     :statute/title "Companies Act 71 of 2008"
     :statute/jurisdiction "ZAF"
     :statute/kind :law
     :statute/law-number "Act No. 71 of 2008"
     :statute/url "https://www.justice.gov.za/legislation/acts/2008-071.pdf"
     :statute/url-provenance :official-justice-gov-za
     :statute/enacted-date "2009-04-08"
     :statute/last-revised-date "2024-12-27"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "zaf.popia-act-4-2013"
     :statute/title "Protection of Personal Information Act 4 of 2013 (POPIA)"
     :statute/jurisdiction "ZAF"
     :statute/kind :law
     :statute/law-number "Act No. 4 of 2013"
     :statute/url "https://www.justice.gov.za/legislation/acts/2013-004.pdf"
     :statute/url-provenance :official-justice-gov-za
     :statute/enacted-date "2013-11-19"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "zaf.labour-relations-act-66-1995"
     :statute/title "Labour Relations Act, 1995"
     :statute/jurisdiction "ZAF"
     :statute/kind :law
     :statute/law-number "Act No. 66 of 1995"
     :statute/url "https://www.gov.za/sites/default/files/gcis_document/201409/act66-1995labourrelations.pdf"
     :statute/url-provenance :official-gov-za
     :statute/enacted-date "1995-11-29"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-zaf statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "ZAF")) " ZAF statutes seeded with "
                 "official justice.gov.za/gov.za citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
