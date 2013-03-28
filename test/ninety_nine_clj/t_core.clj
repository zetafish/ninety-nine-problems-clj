(ns ninety_nine_clj.t-core
  (:use midje.sweet)
  (:use [ninety_nine_clj.core])
  (:use [clojure.set]))

(fact "P01 - Find the last box of a list"
      (my-last '(a b c d)) => '(d))

(fact "P02 - Find the last but one box of a list"
      (my-but-last '(a b c d)) => '(c d))

(fact "P03 - Find the Kth element of a list"
      (element-at '(a b c d e) 3) => 'c)

(fact "P04 - Find number of elements of a list"
      (count '(a b c d)) =>  4)

(fact "P05 - Reverse a list"
      (reverse '(a b c)) => '(c b a))

(fact "P06 - Find out if a list is a palindrome"
      (palindrome? '(a b c)) => false
      (palindrome? '(a b a)) => true)

(fact "P07 - flatten a nested list"
      (flatten '(a (b c) (((d))))) => '(a b c d))

(fact "P08 - eliminate consequetive duplicates"
      (remove-dups '(a a a a b c c a a d e e e e)) => '(a b c a d e))

(fact "P09 - pack consequtive duplicates"
      (pack '(a a a a b c c a a d e e e e)) => '((a a a a) (b) (c c) (a a) (d) (e e e e)))

(fact "P10 - run length encoding"
      (encode '(a a a a b c c a a d e e e e)) => '((4 a) (1 b) (2 c) (2 a) (1 d) (4 e)))

(fact "P11 - modified run length"
      (encode-modified '(a a a a b c c a a d e e e e)) => '((4 a) b (2 c) (2 a) d (4 e)))

(fact "P12 - decode a run length list"
      (decode '((4 a) b (2 c))) => '(a a a a b c c))

;; TODO P13 

(fact "P14 - duplicate elements of a list"
      (dupli '(a b c c d)) => '(a a b b c c c c d d))

(fact "P15 - Replicate the elements of a list a given number of times"
      (repli '(a b c) 3) => '(a a a b b b c c c))

(fact "P16 - Drop envery Nth element from a list"
      (drop-nth '(a b c d e f g h i k) 3) => '(a b d e g h k))

(fact "P17 - Split a list into two parts"
      (split '(a b c d e f g h i k) 3) => '((a b c) (d e f g h i k)))

(fact "P18 - Extract a slice from a list"
      (slice '(a b c d e f g h i k) 3 7) => '(c d e f g))

(fact "P19 - Rotate a list N places to the left"
      (rotate '(a b c d e f g h i k) 3) => '(d e f g h i k  a b c))

(fact "P20 - Remove the k-th element from a list"
      (remove-at '(a b c d) 2) => '(a c d))

(fact "P21 - Insert an element at a given position"
      (insert-at 'alfa '(a b c d) 2) => '(a alfa b c d))

(fact "P22 - Create a list containing integers in a given range"
      (my-range 4 9) => '(4 5 6 7 8 9))

(fact "P23 - Extract a given number of randomly selected elements from a list"
      (count (rnd-select '(a b c d e f g h) 3)) => 3
      (difference (set (rnd-select '(a b c d e f g h) 3))
                  (set '(a b c d e f g h))) =>  #{})

(fact "P24 - Lotto: Draw N different random numers from 1..M"
      (count (lotto-select 6 49)) => 6
      (lotto-select 1 1) => '(1)
      (reduce min 100 (lotto-select 1000 49)) => 1
      (reduce max -1 (lotto-select 1000 49)) => 49)

(fact "P25 - Generate a random permutation of the elements of a list"
      (count (rnd-permu '(a b c d e f))) => 6)

