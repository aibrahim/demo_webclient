(ns webclient.panels.users.register.events
  (:require [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [webclient.events :as events]
            [webclient.ajax :as ajax]
            [webclient.config :as config]))

(rf/reg-event-db
 :register/set-username
 (fn [db [_ username]]
   (assoc-in db [:register :username] username)))

(rf/reg-event-db
 :register/set-role
 (fn [db [_ role]]
   (assoc-in db [:register :role] role)))


(rf/reg-event-db
 :register/set-password
 (fn [db [_ password]]
   (assoc-in db [:register :password] password)))

(rf/reg-event-fx
 :register/click
 (fn [{db :db} _]
   (ajax/post-request (str config/api-url "/user")
                      (:register db)
                      [:register/success]
                      [:register/failure])))

(rf/reg-event-fx
 :register/success
 (fn [{:keys [db]} [_ response]]
   {:db (-> db
            (dissoc :register/password-errors)
            (dissoc :register)
            (assoc :register/alert "Your account is created successfully"))
    :dispatch [::events/navigate :login]}))

(rf/reg-event-db
 :register/failure
 (fn [db [_ {:keys [response]}]]
   (condp = (:reason response)
     "create-user.error/password-violations" (assoc-in db [:register/password-errors] (:violations response))
     db)))

(rf/reg-event-db
 :register/clear-alert
 (fn [db _]
   (dissoc db :register/alert)))

(rf/reg-event-db
 :register/clear-errors
 (fn [db _]
   (dissoc db :register/password-errors)))
