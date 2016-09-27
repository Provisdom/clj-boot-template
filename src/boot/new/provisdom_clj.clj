(ns boot.new.provisdom-clj
  "Generate a basic application project."
  (:require [boot.new.templates :as boot-new]))

(defn provisdom-clj
  "A provisdom application project template."
  [name]
  (let [render (boot-new/renderer "provisdom-clj")
        main-ns (boot-new/multi-segment (boot-new/sanitize-ns name))
        data {:raw-name    name
              :name        (boot-new/project-name name)
              :namespace   main-ns
              :nested-dirs (boot-new/name-to-path main-ns)
              :year        (boot-new/year)
              :date        (boot-new/date)}]
    (println "Generating a project called" name "based on the 'provisdom-clj' template.")
    (boot-new/->files
      data
      ["build.boot" (render "build.boot" data)]
      ["README.md" (render "README.md" data)]
      ["doc/intro.md" (render "intro.md" data)]
      [".gitignore" (render "gitignore" data)]
      ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
      ["test/{{nested-dirs}}_test.clj" (render "test.clj" data)]
      ["LICENSE" (render "LICENSE" data)]
      ["CHANGELOG.md" (render "CHANGELOG.md" data)]
      [".envrc" (render "envrc" data)])))