(ns boot.new.provisdom-clj
  "Generate a basic application project."
  (:require [boot.new.templates :as boot-new]
            [clojure.string :as str]))

(defn has-core?
  [s]
  (= (last (str/split s #"\.")) "core"))

(defn name->dirs
  [n]
  (as-> n $ (str/split $ #"\.") (butlast $) (str/join java.io.File/separator $) (boot-new/sanitize $)))

(defn provisdom-clj
  "A provisdom application project template."
  [name]
  (let [render (boot-new/renderer "provisdom-clj")
        main-ns (let [n (boot-new/multi-segment (boot-new/sanitize-ns name))]
                  (if (has-core? n) n (str n ".core")))
        data {:raw-name      name
              :name          (boot-new/project-name name)
              :namespace     main-ns
              :test-ns       (str main-ns "-test")
              :main-ns-refer (str/join (take 2 (boot-new/project-name name)))
              :nested-dirs   (boot-new/name-to-path main-ns)
              :parent-dirs   (name->dirs main-ns)
              :year          (boot-new/year)
              :date          (boot-new/date)}]
    (println "Generating a project called" name "based on the 'provisdom-clj' template.")
    (boot-new/->files
      data
      ["build.boot" (render "build.boot" data)]
      ["README.md" (render "README.md" data)]
      ["doc/intro.md" (render "intro.md" data)]
      [".gitignore" (render "gitignore" data)]
      ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
      ["test/{{parent-dirs}}/t_core.clj" (render "test.clj" data)]
      ["LICENSE" (render "LICENSE" data)]
      ["CHANGELOG.md" (render "CHANGELOG.md" data)]
      [".envrc" (render "envrc" data)]
      [".gitlab-ci.yml" (render "gitlab-ci.yml" data)])))