(ns webclient.routes
  (:require [re-frame.core :as re-frame]
            [webclient.subs :as subs]
            [webclient.events :as events]
            [webclient.effects :as effects]
            [webclient.views :refer [layout-1 dashboard-panel]]
            [webclient.panels.users.login.views :refer [login-panel]]
            [webclient.panels.users.register.views :refer [register-panel]]
            [webclient.db :refer [match]]
            [reitit.core :as r]
            [reitit.coercion :as rc]
            [reitit.coercion.spec :as rss]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(def routes
  ["/"
   [""
    {:name      :dashboard
     :view      dashboard-panel
     :link-text "Dashboard"
     :controllers
     [{;; Do whatever initialization needed for Dashboard page
       ;; I.e (re-frame/dispatch [::events/load-something-with-ajax])
       :start (fn [& params]
                (re-frame/dispatch [:sidebar/set-active-item :dashboard]))
       ;; Teardown can be done here.
       :stop  (fn [& params] (js/console.log "Leaving home page"))}]}]
   ["login"
    {:name      :login
     :view     login-panel
     :link-text "Login"
     :controllers
     [{:start (fn [& params] (js/console.log "Entering login"))
       :stop  (fn [& params] (js/console.log "Leaving login"))}]}]
   ["register"
    {:name      :register
     :view      register-panel
     :link-text "Register"
     :controllers
     [{:start (fn [& params] (js/console.log "Entering register"))
       :stop  (fn [& params] (js/console.log "Leaving register"))}]}]])

(defn on-navigate [new-match]
  (when new-match
    (re-frame/dispatch [::events/navigated new-match])))

(def router
  (rf/router
   routes
   {:data {:coercion rss/coercion}}))

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
   router
   on-navigate
   {:use-fragment true}))

(defn router-component [{:keys [router]}]
  (let [current-route @(re-frame/subscribe [::subs/current-route])
        route-name (-> current-route :data :name)
        user-id @(re-frame/subscribe [::subs/current-user-id])]
    (if user-id
      [layout-1
       (when current-route
         (reset! match current-route)
         [(-> current-route :data :view)])]
      (if (#{:register} route-name)
        [(-> current-route :data :view)]
        [login-panel]))))

