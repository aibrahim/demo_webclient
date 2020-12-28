(ns webclient.panels.users.register.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :register/username
 (fn [db _]
   (get-in db [:register :username])))

(rf/reg-sub
 :register/role
 (fn [db _]
   (get-in db [:register :role])))

(rf/reg-sub
 :register/password
 (fn [db _]
   (get-in db [:register :password])))

(rf/reg-sub
 :register/password-errors
 (fn [db _]
   (get db :register/password-errors)))

(rf/reg-sub
 :register/alert
 (fn [db _]
   (:register/alert db)))
