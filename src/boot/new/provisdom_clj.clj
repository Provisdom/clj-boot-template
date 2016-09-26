(ns boot.new.provisdom-clj
  "Generate a basic application project."
  (:require [boot.new.templates :refer [renderer year date project-name
                                        ->files sanitize-ns name-to-path
                                        multi-segment]]))

(defn provisdom-clj
  "A provisdom application project template."
  [name]
  (let [render (renderer "provisdom-clj")
        main-ns (multi-segment (sanitize-ns name))
        data {:raw-name    name
              :name        (project-name name)
              :namespace   main-ns
              :nested-dirs (name-to-path main-ns)
              :year        (year)
              :date        (date)}]
    (println "Generating a project called" name "based on the 'provisdom-clj' template.")
    (->files data
             ["build.boot" (render "build.boot" data)]
             ["README.md" (render "README.md" data)]
             ["doc/intro.md" (render "intro.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}_test.clj" (render "test.clj" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["CHANGELOG.md" (render "CHANGELOG.md" data)]
             [".envrc" (render "envrc" data)])))