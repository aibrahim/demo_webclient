(ns webclient.db
  (:require [reagent.core :as reagent]))

(defonce match (reagent/atom nil))

(def default-db
  {:name "Demo webclient"})


