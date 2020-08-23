(ns exif
  (:require [exif-processor.core :as exif.core]))

(defn exif-from-file
  [file]
  (exif.core/exif-for-file file))

