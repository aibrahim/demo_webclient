(ns webclient.events
  (:require
   [re-frame.core :as re-frame]
   [webclient.db :as db]
   [webclient.effects :as effects]
   [reitit.frontend.controllers :as rfc]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-fx
 ::navigate
 (fn [db [_ & route]]
   ;; See `navigate` effect in routes.cljs
   {::effects/navigate! route}))

(re-frame/reg-event-db
 ::navigated
 (fn [db [_ new-match]]
   (let [old-match   (:current-route db)
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     (assoc db :current-route (assoc new-match :controllers controllers)))))

(re-frame/reg-event-db
 :sidebar/set-active-item
 (fn [db [_ item]]
   (assoc-in db [:sidebar :active-item] item)))

(re-frame/reg-event-db
 ::set-current-user
 (fn [db [_ user]]
   (assoc db :current-user user)))
