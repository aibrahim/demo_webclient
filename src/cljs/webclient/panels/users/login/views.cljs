(ns webclient.panels.users.login.views
  (:require
   [re-frame.core :as rf]
   [webclient.subs]
   [webclient.events]
   [webclient.panels.users.login.subs]
   [webclient.panels.users.login.events]
   [reitit.frontend.easy :as rfe]
   [webclient.panels.users.views :refer [wrap-form]]))

(defn login-username-ui []
  [:div {:class "field"}
   [:label {:class "label"} "Username"]
   [:div {:class "control has-icons-left"}
    [:input {:value @(rf/subscribe [:login/username])
             :on-change #(rf/dispatch [:login/set-username (-> % .-target .-value)])
             :class "input"
             :type "username"
             :placeholder "Adam"}]
    [:span {:class "icon is-small is-left"}
     [:i {:class "fas fa-user"}]]]])

(defn login-password-ui []
  [:div {:class "field"}
   [:label {:class "label"} "Password"]
   [:div {:class "control has-icons-left"}
    [:input {:value @(rf/subscribe [:login/password])
             :on-change #(rf/dispatch [:login/set-password (-> % .-target .-value)])
             :class "input"
             :type "password"
             :placeholder "********"}]
    [:span {:class "icon is-small is-left"}
     [:i {:class "fas fa-lock"}]]]])

(defn login-btn-ui []
  [:div {:class "field"}
   [:button {:on-click #(do
                          (.preventDefault %)
                          (rf/dispatch [:login/click]))
             :class "button is-success is-medium is-fullwidth"}
    "Login"]])

(defn account-txt-ui []
  [:p
   "Don't have an account? "
   [:a {:href (rfe/href :register)} "Register for free"]])

(defn login-form []
  [:form {:class "box"}
   [login-username-ui]
   [login-password-ui]
   [login-btn-ui]
   [account-txt-ui]])

(defn login-panel []
  [wrap-form
   login-form])
