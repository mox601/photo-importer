(ns io
  (:require [clojure.string :as str]
            [clojure.java.io :as cjio]
            [exif :as exif]))

;; TODO make it case insensitive
(defn files-in-path
  [path ext]
  (filter #(str/ends-with? (.getName %) ext)
          (file-seq (cjio/file path))))

(defn copy-file
  [source-path dest-path]
  (println source-path dest-path)
  (cjio/copy (cjio/file source-path) (cjio/file dest-path)))

(defn mkdir
  [path]
  (.mkdir (java.io.File. path)))

(defn date-time->filename
  [date-time-str]
  (let [date-time (str/split date-time-str #" ")]
    (str (str/replace (first date-time) #":" "-")
         " "
         (str/replace (second date-time) #":" "."))))

(defn date-time-original
  [m]
  (get m "Date/Time Original"))

;;(date-time->filename "2020:08:19 12:03:48")

(defn extension
  [file]
  (let [filename (.getName file)]
    (subs filename
          (dec (.lastIndexOf filename "."))
          (.length filename))))

(defn date-time-filename
  [file]
  (str (date-time->filename (date-time-original (exif/exif-from-file file)))
       (extension file)))

(defn input-files->output-files
  [input-path output-path]
  (reduce (fn [m v]
            (assoc m (.getPath v) (str output-path (date-time-filename v))))
        {}
        (files-in-path input-path "CR2")))

