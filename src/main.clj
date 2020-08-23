(ns main
  (:require [io :as io]))

(def test-path "/Volumes/TOSHIBA2/Dati/Foto/digitali/2020_03_26 Casa")
;; (io/input-files->output-files test-path "/output/path/")

(def desktop-new-folder "/Users/mox/Desktop/new-folder/")

;; output-path must exist
(defn run
  [{:keys [input-path output-path]}]
  (map #(io/copy-file (first %) (second %))
       (io/input-files->output-files input-path output-path)))

(defn -main [& args]
  (println (run {
             :input-path (first args)
             :output-path (second args)})))

;; command line:
;; import-photos /input/path/ /output/path/
;; TODOs
;; ensure paths are correct
;; mkdir output path
;; print stats on imported files (number, shot between date x and y, ...)
;; group pictures by day
;; merge days to reach a minimum of N pictures per folder

