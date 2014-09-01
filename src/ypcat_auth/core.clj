(ns ypcat-auth.core
  (:require [clojure.string :as s])
  (:require [me.raynes.conch :refer [with-programs]])
  (:import Crypt))

(defn- passwd-for-name [name txt]
  (first (filter identity
                 (for [line (s/split-lines txt)]
                   (let [[nm pw _ _ _ _ she] (s/split line #":")]
                     (when (and (= nm name)
                                (not (re-find #"^[*xX]$" pw))
                                (not (re-find #"nologin$" she)))
                       pw))))))

(defn unix-crypt [salt txt]
  (. Crypt crypt salt txt))

(defn ypcat-passwd-authenticated? [username password]
  (let [pdb (with-programs [ypcat] (ypcat "passwd" {:throw false}))
        pwd (passwd-for-name username pdb)]
    (if pwd
      (= (unix-crypt pwd password) pwd)
      false)))
