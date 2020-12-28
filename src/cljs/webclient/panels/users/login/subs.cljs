(ns webclient.panels.users.login.subs
  (:require [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [webclient.events :as events]
            [webclient.ajax :as ajax]))

(rf/reg-sub
 :login/username
 (fn [db _]
   (get-in db [:login :username])))

(rf/reg-sub
 :login/password
 (fn [db _]
   (get-in db [:login :password])))

(rf/reg-sub
 :login/errors
 (fn [db _]
   (db :login/errors)))

(rf/reg-sub
 :login/alert
 (fn [db _]
   (:login/alert db)))
