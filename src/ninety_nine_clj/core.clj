(ns ninety_nine_clj.core
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower]))

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
  (let [p (permutations col)]
    (nth p (rand-int (count p)))))

(def combos combinations)

(defn group3 [col]
  (list (slice col 1 2) (slice col 3 5) (slice col 6 9)))

(defn group [col sizes]
  (if (empty? sizes)
    ()
    (cons (take (first sizes) col)
          (group (drop (first sizes) col) (rest sizes)))))

(defn make-sort [mf]
  (fn [col] (sort (fn [x y] (< (mf x) (mf y))) col)))

(def lsort (make-sort count))

(defn lfsort [col]
  ((make-sort (fn [s] (count (filter #(= % (count s))
                                    (map count col)))))
   col))

(defn prime? [n]
  (not (some #(zero? %)
             (map #(mod n %)
                  (range 2 (inc (/ n 2)))))))

(def my-gcd gcd)
(defn coprime [a b] (= 1 (gcd a b)))

(defn totient-phi [n]
  (count (filter identity
                 (map #(coprime n %)
                      (range 1 (inc n))))))

(defn primes-below [n]
  (loop [vv (range 2 n) acc ()]
    (if (empty? vv)
      (reverse acc)
      (recur (remove #(zero? (mod % (first vv))) vv)
             (cons (first vv) acc)))))

(let [pp (primes-below 5000)]
  (defn primes-below [n]
    (take-while #(< % n) pp)))


(defn prime-factors [n]
  (if (= 1 n)
    (list 1)
    (loop [pp (primes-below (inc (/ n 2)))
           num n
           acc ()]
      (if (empty? pp)
        (reverse acc)
        (let [p (first pp)]
          (if (zero? (mod num p))
            (recur pp (/ num p) (cons p acc))
            (recur (rest pp) num acc)))))))

(defn prime-factors-mult [n]
  (map reverse (encode (prime-factors n))))

(defn totient-phi-impr [n]
  (reduce + (map (fn [s]
                   (let [p (first s)
                         m (second s)]
                     (* (dec p)
                        (expt p (dec m)))))
                 (prime-factors-mult n))))

(defn goldbach [n]
  (let [pp (primes-below n)]
    (first (filter #(= n (reduce + %))
                   (cartesian-product pp pp)))))

(defn goldbach-list [a b]
  (map #(cons % (goldbach %))
       (filter even?
               (range a (inc b)))))

(defn goldbach-list-lim [a b lim]
  (filter (fn [s] (and (= 3 (count s))
                      (> (nth s 1) lim)
                      (> (nth s 2) lim)))
          (goldbach-list a b)))

