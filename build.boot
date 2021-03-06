(def project 'provisdom-clj/boot-template)
(def version "0.4.0")

(set-env! :resource-paths #{"src"}
          :dependencies '[[org.clojure/clojure "1.9.0"]
                          [adzerk/bootlaces "0.1.13"]
                          [seancorfield/boot-new "0.4.7"]])

(task-options!
  pom {:project     project
       :version     version
       :description "The provisdom boot-new template"
       :url         "https://github.com/Provisdom/clj-boot-template"
       :scm         {:url "https://github.com/Provisdom/clj-boot-template"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}}
  push {:gpg-sign false})

(require '[adzerk.bootlaces :refer [bootlaces! build-jar push-snapshot push-release]])

(bootlaces! version)

(deftask build []
         (comp (pom) (jar) (install)))

(deftask deploy
         []
         (comp (pom) (jar) (push)))