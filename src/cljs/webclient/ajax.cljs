(ns webclient.ajax
  (:require [ajax.core :as ajax]))

(defn get-request
  [uri on-success on-error & {:keys [headers] :or {headers {}}}]
  {:http-xhrio {:method          :get
                :uri             uri
                :format          (ajax/json-request-format)
                :headers         (merge {"Access-Control-Allow-Headers" "Content-Type"
                                         "Access-Control-Allow-Origin" "*"} headers)
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success      on-success
                :on-failure      on-error}})

(defn post-request
  [uri params on-success on-error & {:keys [headers body progress-handler] :or {headers {} body nil progress-handler #()}}]
  {:http-xhrio {:method          :post
                :uri             uri
                :params          params
                :body            body
                :format          (ajax/json-request-format)
                :headers         (merge {"Access-Control-Allow-Headers" "Content-Type"
                                         "Access-Control-Allow-Origin" "*"
                                         } headers)
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success      on-success
                :on-failure      on-error
                :progress-handler progress-handler}})
