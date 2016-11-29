(def project '{{raw-name}})
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"src"}
          :source-paths #{"test"}
          :dependencies '[[provisdom/boot-tasks "RELEASE" :scope "test"]
                          [adzerk/boot-test "RELEASE" :scope "test"]

                          [org.clojure/clojure "RELEASE"]])

(require '[adzerk.boot-test :refer [test]]
         '[provisdom.boot-tasks.core :refer [build push-jar]])

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