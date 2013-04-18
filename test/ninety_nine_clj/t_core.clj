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
      (count (rnd-permu '(a b c d e f))) => 6
      (set (rnd-permu '(a b c d))) => '#{a b c d})

(fact "P26 - Generate combinations of K distinct objects from list of N"
      (combos '(a b c) 2) => '((a b) (a c) (b c)))

(fact "P27 - Group the elements of a set into disjoint subsets"
      (group3 '(a b c d e f g h i)) => '((a b) (c d e) (f g h i))
      (group '(a b c d e f g h i) '(2 2 5)) => '((a b) (c d) (e f g h i)))

(fact "P28 - Sorting a list of lists according to a length of sublists"
      (let [x '((a b c) (d e) (f g h) (d e) (i j k l) (m n) (o))]
        (lsort  x) => '((o) (d e) (d e) (m n) (a b c) (f g h) (i j k l))
        (lfsort x) => '((i j k l) (o) (a b c) (f g h) (d e) (d e) (m n))))

(fact "P31 - Determine whether a give integer number is prime"
      (fact "31" (prime? 31) => true)
      (fact "31" (prime? 31) => true)
      (fact "32" (prime? 32) => false)
      (fact "7"  (prime? 7) => true)
      (fact "4" (prime? 4) => false))

(fact "P32 - Determine gcd"
      (my-gcd 4 6) => 2
      (my-gcd 36 63) => 9)

(fact "P33 - Determine if numbers are co-prime"
      (coprime 35 64) => true)

(fact "P34 - calculate Euler''s totient function phi(m)"
      (fact "1" (totient-phi 1) => 1)
      (fact "10" (totient-phi 10) => 4)
      (fact "500" (totient-phi 500) => 200))

(fact "P35 - determine prime factors of a positive integer"
      (prime-factors 315) => '(3 3 5 7))

(fact "P36 - prime factors run-length encoded"
      (prime-factors-mult 1) => '((1 1))
      (prime-factors-mult 10) => '((2 1) (5 1))
      (prime-factors-mult 315) => '((3 2) (5 1) (7 1)))

(fact "P38 - calculate Euler''s totient function phi(m)"
      (fact "1" (totient-phi-impr 1) => 1)
      (fact "10" (totient-phi-impr 10) => 4)
      (fact "500" (totient-phi-impr 500) => 200))

(fact "P40 - Goldbach's conjecture"
      (fact "28" (goldbach 28) => '(5 23)))

(fact "P41 - list of glodbach compositions"
      (goldbach-list 9 20) => '((10 3 7)
                                (12 5 7)
                                (14 3 11)
                                (16 3 13)
                                (18 5 13)
                                (20 3 17)))
