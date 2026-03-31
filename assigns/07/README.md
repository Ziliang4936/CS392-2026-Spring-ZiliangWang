# Assignment 7

It is due Tuesday, the 4th of November, 2025.

## Assignment 7-1 (40 points)

Given a (possibly infinite) linear stream (LnStrm) of ordered linear
streams where the first elements of these ordered linear streams are
also ordered (that is, the first element of the first stream is less
than the first element of the second stream, which is less than the
first element of the third stream, and so on, and so forth), you are
asked to implement a static method to merge them into one single ordered
linear stream.

## Assignment 7-2 (20 points)

Based on your solution to Assign07_01, please construct a linear
stream of all the positive integer pairs (x, y) satisfying 0 < x <=
y that are ordered according to the sum of x^3 + y^3.  For instance,
(2, 2) should appear in front of (1, 3) since 2^3+2^3=16<28=1^3+3^3.
Based this stream of integer pairs, please construct a stream of
Ramanujan numbers (whose definition can be readily found on-line).
