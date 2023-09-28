#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file

;; put your code below

; Helper functions

(define ones (lambda () (cons 1 ones)))
(define powers-of-two
  (letrec ([f (lambda (x) (cons x (lambda () (f (* x 2)))))])
    (lambda () (f 2))))

; ==========
; Problem 1
; ==========
(define (sequence low high stride)
  (if (> low high)
      null
      (cons low (sequence (+ low stride) high stride))))

; ==========
; Problem 2
; ==========
(define (string-append-map xs suffix)
  (map (lambda (x) (string-append x suffix)) xs))

; ==========
; Problem 3
; ==========
(define (list-nth-mod xs n)
  (cond [(< n 0) (error "list-nth-mod: negative number")]
        [(null? xs) (error "list-nth-mod: empty list")]
        [#t (let ([i (remainder n (length xs))])
              (car (list-tail xs i)))]))

; ==========
; Problem 4
; ==========
(define (stream-for-n-steps s n)
  (letrec ([f (lambda (s step acc)
                (let ([pr (s)])
                  (if (= step n)
                      acc
                      (f (cdr pr) (+ step 1) (append acc (list (car pr)))))))])
    (f s 0 null)))

; ==========
; Problem 5
; ==========
(define funny-number-stream
  (letrec ([f (lambda (x) (cons (if (= (remainder x 5) 0) (- x) x)
                                (lambda () (f (+ x 1)))))])
    (lambda () (f 1))))

; ==========
; Problem 6
; ==========
(define dan-then-dog
  (letrec ([f (lambda (x) (cons (if (= (remainder x 2) 0) "dan.jpg" "dog.jpg")
                                (lambda () (f (+ x 1)))))])
    (lambda () (f 0))))

; ==========
; Problem 7
; ==========
(define (stream-add-zero s)
  (lambda ()
    (cons (cons 0 (car (s)))
          (stream-add-zero (cdr (s))))))

; ==========
; Problem 8
; ==========
(define (cycle-lists xs ys)
  (letrec ([f (lambda (n)
                (cons (cons (list-nth-mod xs n)
                            (list-nth-mod ys n))
                      (lambda () (f (+ n 1)))))])
    (lambda () (f 0))))

; ==========
; Problem 9
; ==========
(define (vector-assoc v vec)
  (letrec ([f (lambda (n)
                (cond [(equal? n (vector-length vec))
                       #f]
                      [(letrec ([elem (vector-ref vec n)])
                         (and (cons? elem) (equal? v (car elem))))
                       (vector-ref vec n)]
                      [#t
                       (f (+ n 1))]))])
    (f 0)))

; ==========
; Problem 10
; ==========
(define (cached-assoc xs n)
  (letrec ([cache (make-vector n #f)]
           [pos 0]
           [f (lambda (v)
                (let ([ans (vector-assoc v cache)])
                  (if ans
                      (cdr ans)
                      (let ([new-ans (assoc v xs)])
                        (if new-ans
                            (begin
                              (vector-set! cache pos new-ans)
                              (set! pos (if (= pos (- n 1))
                                            0
                                            (+ pos 1)))
                              new-ans)
                            #f)))))])
    f))
           











