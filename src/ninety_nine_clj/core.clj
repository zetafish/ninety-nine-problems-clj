(ns ninety_nine_clj.core)

(defn my-last [col]
  (list (last col)))

(defn my-but-last [col]
  (reverse (take 2 (reverse col))))

(defn element-at [col n]
  (nth col (- n 1)))

(defn palindrome? [col]
  (= col (reverse col)))

(defn remove-dups [col]
  (map first (partition-by identity col)))

(defn pack [col]
  (partition-by identity col))

(defn encode [col]
  (map #(list (count %) (first %))
       (pack col)))

(defn encode-modified [col]
  (map #(if (= 1 (first %)) (second %) %)
   (encode col)))

(defn decode [col]
  (flatten (map (comp #(repeat (first %) (second %))
                      #(if (seq? %) % (list 1 %)))
         col)))

(defn dupli [col]
  (flatten (map #(repeat 2 %) col)))

(defn repli [col n]
  (flatten (map #(repeat n %) col)))

(defn drop-nth [col n]
  (flatten (map #(take (dec n) %) (partition-all n col))))

(defn split [col n]
  (list (take n col) (drop n col)))

(defn slice [col i k]
  (drop (dec i) (take k col)))

(defn rotate [col n]
  (concat (drop n col) (take n col)))

(defn remove-at [col n]
  (concat (take (dec n) col) (drop n col)))

(defn insert-at [item col n]
  (concat (take (dec n) col) (list item) (drop (dec n) col)))

(defn my-range [a b]
  (range a (inc b)))

(defn rnd-select [col n]
  (map #(nth col %)
       (map rand-int (repeat n (count col)))))

(defn lotto-select [n m]
  (rnd-select (my-range 1 m) n))

(defn rnd-permu [col]
  )
