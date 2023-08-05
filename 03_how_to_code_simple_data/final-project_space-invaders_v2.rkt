;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname final-project_space-invaders_v2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/universe)
(require 2htdp/image)

;; Space Invaders


;; Constants:

(define WIDTH  300)
(define HEIGHT 500)

(define INVADER-X-SPEED 1.5)  ;speeds (not velocities) in pixels per tick
(define INVADER-Y-SPEED 1.5)
(define INVADER-SPEED 2) ; regulate the general speed of the invaders 

(define TANK-SPEED 4)
(define MISSILE-SPEED 10)

(define HIT-RANGE 10)

(define INVADE-RATE 100)

(define BACKGROUND (empty-scene WIDTH HEIGHT))

(define INVADER
  (overlay/xy (ellipse 10 15 "outline" "yellow")              ;cockpit cover
              -5 6
              (ellipse 20 10 "solid"   "yellow")))            ;saucer

(define TANK
  (overlay/xy (overlay (ellipse 28 8 "solid" "black")       ;tread center
                       (ellipse 30 10 "solid" "green"))     ;tread outline
              5 -14
              (above (rectangle 5 10 "solid" "black")       ;gun
                     (rectangle 20 10 "solid" "black"))))   ;main body

(define TANK-HEIGHT/2 (/ (image-height TANK) 2))

(define MISSILE (ellipse 5 15 "solid" "red"))

(define MTS (rectangle WIDTH HEIGHT "solid" "dim gray"))



;; Data Definitions:

(define-struct game (invaders missiles tank))
;; Game is (make-game  (listof Invader) (listof Missile) Tank)
;; interp. the current state of a space invaders game
;;         with the current invaders, missiles and tank position

;; Game constants defined below Missile data definition

#;
(define (fn-for-game s)
  (... (fn-for-loinvader (game-invaders s))
       (fn-for-lom (game-missiles s))
       (fn-for-tank (game-tank s))))



(define-struct tank (x dir))
;; Tank is (make-tank Number Integer[-1, 1])
;; interp. the tank location is x, HEIGHT - TANK-HEIGHT/2 in screen coordinates
;;         the tank moves TANK-SPEED pixels per clock tick left if dir -1, right if dir 1

(define T0 (make-tank (/ WIDTH 2) 1))   ;center going right
(define T1 (make-tank 50 1))            ;going right
(define T2 (make-tank 50 -1))           ;going left

#;
(define (fn-for-tank t)
  (... (tank-x t) (tank-dir t)))



(define-struct invader (x y dx))
;; Invader is (make-invader Number Number Number)
;; interp. the invader is at (x, y) in screen coordinates
;;         the invader along x by dx pixels per clock tick

(define I1 (make-invader 150 100 12))           ;not landed, moving right
(define I2 (make-invader 150 HEIGHT -10))       ;exactly landed, moving left
(define I3 (make-invader 150 (+ HEIGHT 10) 10)) ;> landed, moving right


#;
(define (fn-for-invader invader)
  (... (invader-x invader) (invader-y invader) (invader-dx invader)))


(define-struct missile (x y))
;; Missile is (make-missile Number Number)
;; interp. the missile's location is x y in screen coordinates

(define M1 (make-missile 150 300))                       ;not hit U1
(define M2 (make-missile (invader-x I1) (+ (invader-y I1) 10)))  ;exactly hit U1
(define M3 (make-missile (invader-x I1) (+ (invader-y I1)  5)))  ;> hit U1

#;
(define (fn-for-missile m)
  (... (missile-x m) (missile-y m)))



(define G0 (make-game empty empty T0))
(define G1 (make-game empty empty T1))
(define G2 (make-game (list I1) (list M1) T1))
(define G3 (make-game (list I1 I2) (list M1 M2) T1))



;; =================
;; Functions:

;; Game -> Game
;; start the world with (main G0)
;; 
(define (main g)
  (big-bang g                    ; Game
    (on-tick   tock)     ; Game -> Game
    (to-draw   render)   ; Game -> Image
    (stop-when invaded?)      ; Game -> Boolean
    ;            (on-mouse  ...)      ; Game Integer Integer MouseEvent -> Game
    (on-key    action)))    ; Game KeyEvent -> Game

;; Game -> Game
;; produce the next game state
(check-expect (tock (make-game (list (make-invader 150 100 12))
                               (list (make-missile 150 300))
                               (make-tank 50 1)))
              (make-game (list (make-invader (+ 150 (* 12 INVADER-X-SPEED)) (+ 100 (* 12 INVADER-Y-SPEED)) 12))
                         (list (make-missile 150 (- 300 MISSILE-SPEED)))
                         (make-tank (+ 50 TANK-SPEED) 1)))
;(define (tock g) G0) ; stub

(define (tock g)
  (make-game (next-loinvader (game-invaders g) (game-missiles g))
             (next-lom (game-missiles g) (game-invaders g))
             (next-tank (game-tank g))))

;; ListOfInvader ListOfMissile -> ListOfInvader
;; produce the filtered state of ListOfInvader
;; Compounded with random function, check-expect is complicated for this function.              
; (define (next-loinvader loi) empty) ; stub

(define (next-loinvader loi lom)
  (alive-only (spawn-invader (descend-all loi)) lom))

;; ListOfInvader -> ListOfInvader
;; descend all invaders at once
(check-expect (descend-all empty) empty)
(check-expect (descend-all (list (make-invader 150 100 12)))
              (list (make-invader (+ 150 (* 12 INVADER-X-SPEED))
                                  (+ 100 (* 12 INVADER-Y-SPEED))
                                  12)))
(check-expect (descend-all (list (make-invader WIDTH 100 12)))
              (list (make-invader (+ WIDTH (* -12 INVADER-X-SPEED))
                                  (+ 100 (* 12 INVADER-Y-SPEED))
                                  -12)))              
; (define (descend-all loi) loi) ; stub

(define (descend-all loi)
  (cond [(empty? loi) empty]
        [else
         (cons (descend-one (first loi)) (descend-all (rest loi)))]))


;; Invader -> Invader
;; produce the next descended state of one invader
(check-expect (descend-one (make-invader 150 100 12))
              (make-invader (+ 150 (* 12 INVADER-X-SPEED))
                            (+ 100 (* 12 INVADER-Y-SPEED))
                            12))
(check-expect (descend-one (make-invader WIDTH 100 12))
              (make-invader (+ WIDTH (* -12 INVADER-X-SPEED))
                            (+ 100 (* 12 INVADER-Y-SPEED))
                            -12))
(check-expect (descend-one (make-invader 0 100 -12))
              (make-invader (+ 0 (* 12 INVADER-X-SPEED))
                            (+ 100 (* 12 INVADER-Y-SPEED))
                            12))
; (define (descend-one inv) inv) ; stub

(define (descend-one inv)
  (cond [(<= (+ (invader-x inv) (* (invader-dx inv) INVADER-X-SPEED)) 0)
         (make-invader (- (+ (invader-x inv) (* (invader-dx inv) INVADER-X-SPEED)))
                       (+ (invader-y inv) (* (abs (invader-dx inv)) INVADER-Y-SPEED))
                       (- (invader-dx inv)))]
        [(>= (+ (invader-x inv) (* (invader-dx inv) INVADER-X-SPEED)) WIDTH)
         (make-invader (- (* WIDTH 2) (+ (invader-x inv) (* (invader-dx inv) INVADER-X-SPEED)))
                       (+ (invader-y inv) (* (abs (invader-dx inv)) INVADER-Y-SPEED))
                       (- (invader-dx inv)))]
        [else
         (make-invader (+ (invader-x inv) (* (invader-dx inv) INVADER-X-SPEED))
                       (+ (invader-y inv) (* (abs (invader-dx inv)) INVADER-Y-SPEED))
                       (invader-dx inv))]))

;; ListOfInvader -> ListOfInvader
;; spawn new invader
(check-random (spawn-invader empty)
              (cond [(= (random INVADE-RATE) 1)
                     (cons (make-invader (random WIDTH) 0 INVADER-SPEED) empty)]
                    [else empty]))

; (define (spawn-invader) loi)
(define (spawn-invader loi)
  (cond [(= (random INVADE-RATE) 1)
         (cons (make-invader (random WIDTH) 0 INVADER-SPEED) loi)]
        [else loi]))

;; ListOfInvader ListOfMissile -> ListOfInvader
;; produce a filtered list of invaders
;; !!!
;; (define (alive-only loi) loi) ; stub
(define (alive-only loi lom)
  (cond [(empty? loi) empty]
        [else
         (if (alive? (first loi) lom)
             (cons (first loi) (alive-only (rest loi) lom))
             (alive-only (rest loi) lom))]))

;; Invader ListOfMissiles -> Boolean
;; produce true if an invader is within a hit range of a missile
;; !!!
; (define (alive? inv lom) false)

(define (alive? inv lom)
  (cond [(empty? lom) true]
        [else
         (if (and (< (- (missile-x (first lom)) HIT-RANGE)
                     (invader-x inv)
                     (+ (missile-x (first lom)) HIT-RANGE))
                  (< (- (missile-y (first lom)) HIT-RANGE)
                     (invader-y inv)
                     (+ (missile-y (first lom)) HIT-RANGE)))
             false
             (alive? inv (rest lom)))]))



;; ListOfMissile -> ListOfMissile
;; produce the next filtered state of ListOfMissile over time
;; Test is complicated for this function
; (define (next-lom lom loi) empty); stub

(define (next-lom lom loi)
  (active-only (fly-all lom) loi))

;; ListOfMissile -> ListOfMissile
;; produce the next state of flying missiles
(check-expect (fly-all empty) empty)
(check-expect (fly-all (list (make-missile 150 300)))
              (list (make-missile 150 (- 300 MISSILE-SPEED))))


; (define (fly-all lom) lom))]

(define (fly-all lom)
  (cond [(empty? lom) empty]
        [else (cons (fly-one (first lom)) (fly-all (rest lom)))]))

;; Missile -> Missile
;; move up the missile
;; !!!
; (define (fly-one m) m)

(define (fly-one m)
 (make-missile (missile-x m) (- (missile-y m) MISSILE-SPEED)))

;        [(< (- (missile-y (first lom)) MISSILE-SPEED) 0)
;         (next-lom (rest lom))]
;        [else (cons (make-missile (missile-x (first lom)) (- (missile-y (first lom)) MISSILE-SPEED))
;                    (next-lom (rest lom)))]))

;; ListOfMissile ListOfInvader -> ListOfMissile
;; filter only the active missiles
;; Test is complicated.
; (active-only lom loi) lom ; stub

(define (active-only lom loi)
  (cond [(empty? lom) empty]
        [else
         (if (active? (first lom) loi)
             (cons (first lom) (active-only (rest lom) loi))
             (active-only (rest lom) loi))]))

;; Missile ListOfInvader -> Boolean
;; produce true if the missile is still active
(check-expect (active? M1 empty) true)
(check-expect (active? (make-missile 150 0) (list I1)) false)
(check-expect (active? (make-missile 100 50) (list (make-invader 105 52 10))) false)
(check-expect (active? (make-missile 100 50) (list (make-invader 112 35 10))) true)

; (define (active? m loi) true) ; stub

(define (active? m loi)
  (cond [(empty? loi) true]
        [(<= (missile-y m) 0) false]
        [else
         (if (and (< (- (missile-x m) HIT-RANGE)
                     (invader-x (first loi))
                     (+ (missile-x m) HIT-RANGE))
                  (< (- (missile-y m) HIT-RANGE)
                     (invader-y (first loi))
                     (+ (missile-y m) HIT-RANGE)))
             false
             (active? m (rest loi)))]))
 
;; Tank -> Tank
;; produce the next state of tank without user intervention
(check-expect (next-tank (make-tank 50 1))
              (make-tank (+ 50 TANK-SPEED) 1))
(check-expect (next-tank (make-tank 50 -1))
              (make-tank (- 50 TANK-SPEED)  -1))
(check-expect (next-tank (make-tank 0 -1))
              (make-tank 0 -1))
(check-expect (next-tank (make-tank WIDTH 1))
              (make-tank WIDTH 1))
; (define (next-tank t) T0) ; stub

(define (next-tank t)
  (cond [(and (<= (+ (tank-x t) (* (tank-dir t) TANK-SPEED)) 0) (= (tank-dir t) -1))
         (make-tank 0 -1)]
        [(and (>= (+ (tank-x t) (* (tank-dir t) TANK-SPEED)) WIDTH) (= (tank-dir t) 1))
         (make-tank WIDTH 1)]
        [else
         (make-tank (+ (tank-x t) (* (tank-dir t) TANK-SPEED)) (tank-dir t))]))


;; Game -> Image
;; render the game state display 
(check-expect (render (make-game empty empty (make-tank 50 1)))
              (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) MTS))
(check-expect (render (make-game (list (make-invader 150 100 12))
                                 (list (make-missile 150 300))
                                 (make-tank 50 1)))
              (place-image INVADER 150 100
                           (place-image MISSILE 150 300
                                        (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) MTS))))
; (define (render g) MTS) ; stub

(define (render g)
  (place-invaders (game-invaders g)
                  (place-missiles (game-missiles g)
                                  (place-tank (game-tank g)))))

;; ListOfInvader -> Image
;; render the invaders
(check-expect (place-invaders empty MTS) MTS)
(check-expect (place-invaders (list (make-invader 150 100 12)) MTS)
              (place-image INVADER 150 100 MTS))
(check-expect (place-invaders (list (make-invader 150 100 12)
                                    (make-invader 200 50 -10))
                              MTS)
              (place-image INVADER 150 100
                           (place-image INVADER 200 50 MTS)))
(check-expect (place-invaders (list (make-invader 150 100 12)
                                    (make-invader 200 50 -10))
                              (place-tank (make-tank 50 1)))
              (place-image INVADER 150 100
                           (place-image INVADER 200 50
                                        (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) MTS))))
; (define (place-invaders loi img) img) ; stub
(define (place-invaders loi img)
  (cond [(empty? loi) img]
        [else (place-image INVADER
                           (invader-x (first loi))
                           (invader-y (first loi))
                           (place-invaders (rest loi) img))]))

;; ListOfMissile -> Image
;; render the missiles
(check-expect (place-missiles empty MTS) MTS)
(check-expect (place-missiles (list (make-missile 150 300)) MTS)
              (place-image MISSILE 150 300 MTS))
(check-expect (place-missiles (list (make-missile 150 300)
                                    (make-missile 250 50))
                              MTS)
              (place-image MISSILE 150 300 (place-image MISSILE 250 50 MTS)))
(check-expect (place-missiles (list (make-missile 150 300)
                                    (make-missile 250 50))
                              (place-tank (make-tank 50 1)))
              (place-image MISSILE 150 300
                           (place-image MISSILE 250 50
                                        (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) MTS))))
; (define (place-missiles lom img) MTS) ; stub
(define (place-missiles lom img)
  (cond [(empty? lom) img]
        [else (place-image MISSILE (missile-x (first lom)) (missile-y (first lom))
                           (place-missiles (rest lom) img))]))

;; Tank -> Image
;; render the tank
(check-expect (place-tank (make-tank 50 1))
              (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) MTS))
; (define (place-tank t img) MTS) ; stub

(define (place-tank t)
  (place-image TANK (tank-x t) (- HEIGHT TANK-HEIGHT/2) MTS))

;; Game -> Boolean
;; returns true if any of the invader has landed
(check-expect (invaded? G0) false)
(check-expect (invaded? (make-game (list I1) (list M1) T0)) false)
(check-expect (invaded? (make-game (list I2) (list M1) T0)) true)
(check-expect (invaded? (make-game (list I3) (list M1) T0)) true)
; (define (invaded? g) false) ; stub

(define (invaded? g)
  (cond [(empty? (game-invaders g)) false]
        [(landed? (first (game-invaders g))) true]
        [else
         (invaded? (make-game (rest (game-invaders g)) (game-missiles g) (game-tank g)))]))

;; Invader -> Boolean
;; return true if the invader has landed
(check-expect (landed? I1) false)
(check-expect (landed? I2) true)
(check-expect (landed? I3) true)
; (define (landed? invader) false) ; stub

(define (landed? invader)
  (>= (invader-y invader) HEIGHT))


;; Game KeyEvent -> Game
;; perform the tank actions
(check-expect (action (make-game empty empty (make-tank 50 1)) "right")
              (make-game empty empty (make-tank 50 1)))
(check-expect (action (make-game empty empty (make-tank 50 1)) "left")
              (make-game empty empty (make-tank 50 -1)))
(check-expect (action (make-game empty empty (make-tank 50 -1)) "right")
              (make-game empty empty (make-tank 50 1)))
(check-expect (action (make-game empty empty (make-tank 50 -1)) "left")
              (make-game empty empty (make-tank 50 -1)))
(check-expect (action (make-game empty empty (make-tank 50 1)) " ")
              (make-game empty
                         (list (make-missile 50 (- HEIGHT TANK-HEIGHT/2)))
                         (make-tank 50 1)))
; (define (action g ke) g)

(define (action g ke)
  (cond [(or (key=? ke "left") (key=? ke "right"))
         (make-game (game-invaders g) (game-missiles g) (move-tank (game-tank g) ke))]
        [(key=? ke " ")
         (make-game (game-invaders g) (shoot (game-missiles g) (tank-x (game-tank g))) (game-tank g))]
        [else g]))

;; Tank KeyEvent -> Tank
;; move Tank to the left or right according to the key event
;; !!!
; (define (move-tank t ke) t) ; stub

(define (move-tank t ke)
  (cond [(and (key=? ke "left") (= (tank-dir t) 1))
         (make-tank (tank-x t) -1)]
        [(and (key=? ke "right") (= (tank-dir t) -1))
         (make-tank (tank-x t) 1)]
        [else t]))

;; ListOfMissile Number -> ListOfMissile
;; spawn new missile
;; !!!
; (define (shoot loi x) empty) ; stub

(define (shoot lom x)
  (cons (make-missile x (- HEIGHT TANK-HEIGHT/2)) lom))