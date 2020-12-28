(ns webclient.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [webclient.events :as events]
   [webclient.config :as config]
   [webclient.routes :refer [init-routes! router-component router]]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (init-routes!)
  (reagent/render [router-component {:router router}]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
