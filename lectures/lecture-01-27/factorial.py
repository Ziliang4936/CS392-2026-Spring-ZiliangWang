def fact1(n): # recursion
    return n*fact1(n-1) if n >= 1 else 1

def fact2(n): # tail-recursion
    def loop(i, r):
        if i > n:
            return r
        else:
            return loop(i+1, r*i)
    return loop(1, 1)

def fact3(n): # loop-based
    i = 1
    r = 1
    while(i <= n):
        r = r * i; i = i + 1
    return r

def fact3(n): # loop-based
    i = 0
    r = 1
    for i in range(n):
        r = r * (i+1)
    return r

print("fact1(10) = ", fact1(10))
print("fact2(10) = ", fact2(10))
print("fact3(10) = ", fact3(10))

def tally(n): # tail-recursion
    def loop(i, r):
        if i > n:
            return r
        else:
            return loop(i+1, r+i)
    return loop(1, 0)

import sys
sys.setrecursionlimit(10000)
print("tally(10) = ", tally(10000))
