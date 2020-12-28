(ns webclient.views
  (:require [re-frame.core :as rf]
            [webclient.subs :as subs]
            [reitit.frontend.easy :as rfe]))

;;; views ;;;

(defn navbar-brand []
  [:div {:class "navbar-brand"}
   [:a {:class "navbar-item"
        :href (rfe/href :dashboard)}
    "Demo webclient"]
   [:div {:class "navbar-burger"}
    [:span] [:span] [:span]]])

(defn navbar-dropdown []
  [:div {:class "navbar-dropdown"}
   [:a {:class "navbar-item"
        :on-click #(rf/dispatch [:logout/click])}
    [:div
     [:span {:class "icon"}
      [:i {:class "fas fa-sign-out-alt"}]]
     " Sign out"]]])

(defn navbar-end []
  [:div {:class "navbar-end"}
   [:div {:class "navbar-item has-dropdown is-hoverable"}
    [:div {:class "navbar-link"}
     @(rf/subscribe [::subs/current-user-id])]
    [navbar-dropdown]]])

(defn navbar-menu []
  [:div {:class "navbar-menu"}
   [:div {:class "navbar-start"}]
   [navbar-end]])

(defn navbar []
  [:nav {:class "navbar has-shadow"}
   [navbar-brand]
   [navbar-menu]])

(defn sidebar []
  (let [active-item @(rf/subscribe [:sidebar/active-item])]
    [:div {:class "column is-4-tablet is-3-desktop is-2-widescreen"}
     [:nav {:class "menu"}
      [:p {:class "menu-label"}
       "Menu"]
      [:ul {:class "menu-list"}
       [:li
        [:a {:href (rfe/href :dashboard)
             :class (if (= active-item :dashboard) "is-active" "")}
         [:span {:class "icon"}
          [:i {:class "fas fa-tachometer-alt"}]]
         " Dashboard"]]]]]))

;; content must have root column class
(defn layout-1 [content]
  [:div
   [navbar]
   [:section {:class "section"}
    [:div {:class "columns"}
     [sidebar]
     content]]])

(defn dashboard-panel []
  [:div {:class "column"}
   [:h1 {:class "title"} "Dashboard"]])
