(def project '{{raw-name}})
(def version "0.1-alpha1")

(set-env! :resource-paths #{"src"}
          :source-paths #{"test"}
          :dependencies '[[provisdom/boot-tasks "RELEASE" :scope "test"]
                          [adzerk/boot-test "RELEASE" :scope "test"]
                          [expound "RELEASE" :scope "test"]
                          [orchestra "RELEASE" :scope "test"]

                          [org.clojure/clojure "RELEASE"]])

(require '[adzerk.boot-test :refer [test]]
         '[expound.alpha]
         '[clojure.spec.alpha]
         '[provisdom.boot-tasks.core :refer [build push-jar]])

(alter-var-root #'clojure.spec.alpha/*explain-out* (constantly expound.alpha/printer))

(task-options!
  pom {:project     project
       :version     version
       :description "FIXME: write description"
       :url         "http://example/FIXME"
       :scm         {:url "https://github.com/yourname/{{name}}"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}}
  jar {:main '{{namespace}}
       :file (str "{{name}}-" version "-standalone.jar")})