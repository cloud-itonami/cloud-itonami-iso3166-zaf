(ns culture.facts
  "Country-level regional-culture catalog for South Africa (ZAF) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-
  municipality-culture-catalog Wave 1, in com-junkawasaki/root). Sibling
  namespace to `marketentry.facts` / `statute.facts` (ADR-2607141700);
  city-level counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"ZAF"
   [{:culture/id "zaf.dish.bobotie"
     :culture/name "Bobotie"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "South African dish of spiced minced meat baked with an egg-based topping, known in the Cape of Good Hope since the 17th century and adopted by the Cape Malay community after being introduced from Europe via the Dutch."
     :culture/url "https://en.wikipedia.org/wiki/Bobotie"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.dish.bunny-chow"
     :culture/name "Bunny chow"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "Indian South African fast-food dish of a hollowed-out loaf of white bread filled with curry, originating among Indian South Africans in Durban, dated to the 1940s."
     :culture/url "https://en.wikipedia.org/wiki/Bunny_chow"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.dish.boerewors"
     :culture/name "Boerewors"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "Sausage that originated in South Africa (Afrikaans for 'farmer's sausage'), regulated to contain at least 90 percent meat or fat from beef, pork, lamb or goat, traditionally grilled at a braai and served with pap."
     :culture/url "https://en.wikipedia.org/wiki/Boerewors"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.product.biltong"
     :culture/name "Biltong"
     :culture/country "ZAF"
     :culture/kind :product
     :culture/summary "Air-dried, cured meat that originated in Southern Africa and became integral to South African culture and identity."
     :culture/url "https://en.wikipedia.org/wiki/Biltong"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.beverage.rooibos"
     :culture/name "Rooibos"
     :culture/country "ZAF"
     :culture/kind :beverage
     :culture/summary "Herbal tea plant endemic to South Africa's Cederberg region in the Western Cape; the EU grants rooibos protected designation of origin (PDO) status, requiring EU-marketed product to be grown in the Cederberg."
     :culture/url "https://en.wikipedia.org/wiki/Rooibos"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.festival.kaapse-klopse"
     :culture/name "Kaapse Klopse"
     :culture/country "ZAF"
     :culture/kind :festival
     :culture/summary "Traditionally Cape Coloured minstrel festival that takes place annually on 2 January in Cape Town, South Africa."
     :culture/url "https://en.wikipedia.org/wiki/Kaapse_Klopse"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.heritage.robben-island"
     :culture/name "Robben Island"
     :culture/country "ZAF"
     :culture/kind :heritage
     :culture/summary "South African National Heritage Site and UNESCO World Heritage Site (designated 1999) where Nelson Mandela was imprisoned for 18 of his 27 years before becoming South Africa's first Black president."
     :culture/url "https://en.wikipedia.org/wiki/Robben_Island"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "zaf.heritage.cradle-of-humankind"
     :culture/name "Cradle of Humankind"
     :culture/country "ZAF"
     :culture/kind :heritage
     :culture/summary "Paleoanthropological site complex about 50 km northwest of Johannesburg in Gauteng province, declared a UNESCO World Heritage Site in 1999 and holding the largest known concentration of human ancestral remains anywhere in the world."
     :culture/url "https://en.wikipedia.org/wiki/Cradle_of_Humankind"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-zaf culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "ZAF"))
                 " ZAF entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
