(ns webclient.config
  (:require-macros [webclient.macros.helpers :refer [inline-resource]])
  (:require [cljs.reader :as edn]))

(def debug?
  ^boolean goog.DEBUG)

(def options (edn/read-string (inline-resource "config/config.edn")))
(def services (:services options))
(def api-serv (:api services))
(def api-url (str (api-serv :url) ":" (api-serv :port)))



