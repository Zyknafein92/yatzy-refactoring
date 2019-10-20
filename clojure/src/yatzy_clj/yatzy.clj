(ns yatzy-clj.yatzy
  (:gen-class))

(defn chance [d1 d2 d3 d4 d5]
  (+ d1 d2 d3 d4 d5))

(defn tally-die [dice]
  (for [i (range 1 7)]
    (apply + (for [die dice] (if (= die i) 1 0))))
  )

(defn yatzy [dice]
  (let [counts (tally-die dice)
        result (first
                 (remove nil?
                         (for [i (range 0 6)]
                           (if (= (nth counts i) 5)
                             50
                             nil
                             )
                           )
                         )
                 )]
    (if (= nil result) 0 50)))

(defn ones [d1 d2 d3 d4 d5]
  (+ (if (= d1 1) 1 0)
     (if (= d2 1) 1 0)
     (if (= d3 1) 1 0)
     (if (= d4 1) 1 0)
     (if (= d5 1) 1 0))
  )

(defn twos [d1 d2 d3 d4 d5]
  (+ (if (= d1 2) 2 0)
     (if (= d2 2) 2 0)
     (if (= d3 2) 2 0)
     (if (= d4 2) 2 0)
     (if (= d5 2) 2 0))
  )

(defn threes [d1 d2 d3 d4 d5]
  (+ (if (= d1 3) 3 0)
     (if (= d2 3) 3 0)
     (if (= d3 3) 3 0)
     (if (= d4 3) 3 0)
     (if (= d5 3) 3 0))
  )

(defn fours [d1 d2 d3 d4 _5]
  (let [dice [d1 d2 d3 d4 _5]
        sum (apply +
                   (for [at (range 0 5)]
                     (if (= 4 (nth dice at)) 4 0)
                     )
                   )]
    sum
    )
  )


(defn fives_ [d1 d2 d3 d4 _5]
  (let [dice [d1 d2 d3 d4 _5]
        s (apply +
                 (for [i (range 0 (count dice))]
                   (if (= 5 (nth dice i)) 5 0)
                   )
                 )]
    s
    )
  )

(defn sixes [d1 d2 d3 d4 _5]
  (let [dice [d1 d2 d3 d4 _5]
        sum (apply +
                   (for [at (range 0 (count dice))]
                     (if (= 6 (nth dice at)) 6 0)
                     )
                   )]
    sum
    )
  )

(defn score_pair [dice]
  (let [counts (tally-die dice)
        result (first
                 (remove nil?
                         (for [at (range 0 6)]
                           (if (>= (nth counts (- 6 at 1)) 2)
                             (* (- 6 at) 2)
                             nil
                             )
                           )
                         )
                 )]
    (if (= nil result) 0 result)))

(defn two-pair [dice]
  (let [tallies (tally-die dice)
        result (->> (range 0 6)
                    (filter #(>= (nth tallies (- 6 % 1)) 2))
                    (map #(- 6 %))
                    (reduce +)
                    (* 2))]
    (if (= nil result) 0 result)))

(defn four-of-a-kind [dice]
  (let [tallies (tally-die dice)
        result (first
                 (remove nil?
                         (for [i (range 0 6)]
                           (if (>= (nth tallies i) 4)
                             (* (+ i 1) 4)
                             nil
                             )
                           )
                         ))]
    (if (= nil result)
      0 result
      )
    )
  )

(defn three-of-a-kind [dice]
  (let [t (tally-die dice)
        result (first
                 (remove nil?
                         (for [i (range 0 6)]
                           (if (>= (nth t i) 3)
                             (* (+ i 1) 3)
                             nil
                             )
                           )
                         ))]
    (if (= nil result)
      0 result
      )
    )
  )


(defn small-straight [dice]
  (let [tallies (for [i (range 1 7)]
                  (apply + (for [die dice] (if (= die i) 1 0))))]
    (if (= true (and
                  (= (nth tallies 0) 1)
                  (= (nth tallies 1) 1)
                  (= (nth tallies 2) 1)
                  (= (nth tallies 3) 1)
                  (= (nth tallies 4) 1)
                  )
           )
      15
      0)))

(defn large-straight [dice]
  (let [tallies (for [i (range 1 7)]
                  (apply + (for [die dice] (if (= die i) 1 0))))]
    (if (= true (and
                  (= (nth tallies 1) 1)
                  (= (nth tallies 2) 1)
                  (= (nth tallies 3) 1)
                  (= (nth tallies 4) 1)
                  (= (nth tallies 5) 1)
                  )
           )
      20
      0)))

(defn full-house [dice]
  (let [tallies (tally-die dice)]
    (+
      (let [result (first
                     (remove nil?
                             (for [at (range 0 6)]
                               (if (>= (nth tallies (- 6 at 1)) 2)
                                 (* (- 6 at) 2)
                                 nil
                                 )
                               )
                             )
                     )]
        (if (= nil result) 0 result))
      (let [result (first
                     (remove nil?
                             (for [i (range 0 6)]
                               (if (>= (nth tallies i) 3)
                                 (* (+ i 1) 3)
                                 nil
                                 )
                               )
                             ))]
        (if (= nil result)
          0 result
          )
        )
      )
    )
  )