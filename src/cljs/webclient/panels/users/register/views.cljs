(ns webclient.panels.users.register.views
  (:require
   [re-frame.core :as rf]
   [webclient.subs :as subs]
   [webclient.events :as events]
   [webclient.panels.users.register.subs]
   [webclient.panels.users.register.events]
   [reitit.frontend.easy :as rfe]
   [webclient.panels.users.views :refer [wrap-form]]))

(defn username-ui []
  [:div {:class "field"}
   [:label {:class "label"} "Username"]
   [:div {:class "control has-icons-left"}
    [:input {:value @(rf/subscribe [:register/username])
             :on-change #(rf/dispatch [:register/set-username (-> % .-target .-value)])
             :class "input"
             :type "username"
             :placeholder "e.g. Abdullah"}]
    [:span {:class "icon is-small is-left"}
     [:i {:class "fas fa-user"}]]]])

(defn role-ui []
  [:div {:class "field"}
   [:label {:class "label"} "Role"]
   [:div {:class "control has-icons-left"}
    [:input {:value @(rf/subscribe [:register/role])
             :on-change #(rf/dispatch [:register/set-role (-> % .-target .-value)])
             :class "input"
             :type "role"
             :placeholder "Writer"}]
    [:span {:class "icon is-small is-left"}
     [:i {:class "fas fa-user"}]]]])

(defn password-ui []
  [:div {:class "field"}
   [:label {:class "label"} "Password"]
   [:div {:class "control has-icons-left"}
    [:input {:value @(rf/subscribe [:register/password])
             :on-change #(rf/dispatch [:register/set-password (-> % .-target .-value)])
             :class (cond-> "input"
                      (not-empty @(rf/subscribe [:register/password-errors])) (str " is-danger"))
             :type "password"
             :placeholder "********"}]
    [:span {:class "icon is-small is-left"}
     [:i {:class "fas fa-lock"}]]]])

(defn signup-btn-ui []
  [:div {:class "field"}
   [:button {:class "button is-success is-medium is-fullwidth"
             :on-click #(do
                          (.preventDefault %)
                          (rf/dispatch [:register/click]))}
    "Sign up"]])

(defn login-txt-ui []
  [:p
   "Already have an account "
   [:a {:href (rfe/href :login)} "Login"]])

(defn register-form []
  [:form {:class "box"}
   [username-ui]
   [role-ui]
   [password-ui]
   [signup-btn-ui]
   [login-txt-ui]])

(defn register-panel []
  [wrap-form
   register-form])


