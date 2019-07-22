(ns clj-nvd.core
  (:require [clojure.java.io :as io]
            [clojure.tools.deps.alpha :as deps]
            [clojure.tools.deps.alpha.reader :as deps.reader]
            [jsonista.core :as json]
            [nvd.task.check]
            [nvd.task.purge-database]
            [nvd.task.update-database]))

(defn make-classpath []
  (let [lib-map (-> (deps.reader/read-deps [(io/file "deps.edn")])
                    (deps/resolve-deps nil))]
    (mapcat :paths (vals lib-map))))

(defn -main [command & args]
  (let [config (try
                  (read-string (slurp "clj-nvd.edn"))
                  (catch java.io.FileNotFoundException _
                    nil))
        temp-file (java.io.File/createTempFile "clj-nvd" ".json")
        path (.getAbsolutePath temp-file)
        classpath (make-classpath)
        opts {:nvd       config
              :classpath classpath
              :cmd-args  args}]
    (spit path (json/write-value-as-string opts))
    (case command
      "check" (nvd.task.check/-main path)
      "purge" (nvd.task.purge-database/-main path)
      "update" (nvd.task.update-database/-main path)
      (do
        (.println *err* (str "No such command: " command))
        (System/exit 1)))))
