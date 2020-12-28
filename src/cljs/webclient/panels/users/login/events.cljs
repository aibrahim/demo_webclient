(ns webclient.panels.users.login.events
  (:require [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [webclient.events :as events]
            [webclient.ajax :as ajax]
            [webclient.config :as config]))

(rf/reg-event-db
 :login/set-username
 (fn [db [_ username]]
   (assoc-in db [:login :username] username)))

(rf/reg-event-db
 :login/set-password
 (fn [db [_ password]]
   (assoc-in db [:login :password] password)))

(rf/reg-event-fx
 :login/click
 (fn [{db :db} _]
   (ajax/get-request (str config/api-url "/user?username="
                          (-> db :login :username js/encodeURIComponent)
                          "&password="
                          (-> db :login :password js/encodeURIComponent))
                     [:login/success]
                     [:login/failure])))

(rf/reg-event-fx
 :login/success
 (fn [{db :db} [_ response]]
   {:db (assoc db :current-user response)
    :dispatch [::events/navigate :dashboard]}))

(rf/reg-event-db
 :login/failure
 (fn [db [_ {r :response}]]
   (assoc db :login/errors [(:reason r)])))

(rf/reg-event-db
 :logout/click
 (fn [db _]
   (dissoc db :current-user)))

(rf/reg-event-db
 :login/clear-alert
 (fn [db _]
   (dissoc db :login/alert)))

(rf/reg-event-db
 :login/clear-errors
 (fn [db _]
   (dissoc db :login/errors)))
