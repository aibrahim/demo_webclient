(ns webclient.panels.users.views
  (:require [re-frame.core :as rf]
            [webclient.subs]))

(defn alert-ui [subs event]
  (when-let [alert @(rf/subscribe subs)]
    [:div {:class "notification is-info"}
     [:button {:class "delete"
               :on-click #(do
                            (.preventDefault %)
                            (rf/dispatch event))}]
     alert]))

(defn errors-ui [subs event]
  (when-let [errors @(rf/subscribe subs)]
    [:div {:class "notification is-danger"}
     [:button {:class "delete"
               :on-click #(do (.preventDefault %)
                              (rf/dispatch event))}]
     (into [:ul]
           (map #(vector :li %)) errors)]))

;; wrap form for login and register
(defn wrap-form [form]
  [:section {:class "hero is-medium is-bold is-primary is-fullheight"}
   [:div {:class "hero-body"}
    [:div {:class "container"}
     [:div {:class "columns is-centered"}
      [:div {:class "column is-5-tablet is-4-desktop is-3-widescreen"}
       [errors-ui [:login/errors] [:login/clear-errors]]
       [alert-ui [:login/alert] [:login/clear-alert]]
       [errors-ui [:register/password-errors] [:register/clear-errors]]
       [alert-ui [:register/alert] [:register/clear-alert]]
       [form]]]]]])

