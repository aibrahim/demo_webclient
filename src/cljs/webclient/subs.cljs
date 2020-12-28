(ns webclient.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
 ::current-route
 (fn [db]
   (:current-route db)))

(rf/reg-sub
 ::current-user-id
 (fn [db]
   (get-in db [:current-user :id])))

(rf/reg-sub
 :sidebar/active-item
 (fn [db]
   (get-in db [:sidebar :active-item])))


