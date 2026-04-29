//
// HX-2026-04-28: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
// Strategy (O(n^2) time):
//   For each index i (right to left), let L[i] be the length of
//   the longest non-decreasing subsequence starting at i, and
//   nxt[i] be the smallest index j > i with xs[j] >= xs[i] and
//   L[j] = L[i] - 1 (i.e. the leftmost choice that maintains the
//   maximum length).
//
//   Building L and nxt takes O(n^2) time: each i scans j > i.
//
//   To return the leftmost longest subsequence, find the smallest
//   start index s with L[s] = max(L), then walk via nxt.
//   Choosing the leftmost start and the leftmost next at each step
//   makes the resulting index sequence lexicographically smallest
//   among all longest non-decreasing subsequences.
//
//   The reconstruction is O(n), so total time is O(n^2).
//
import Library00.FnList.*;
// Please see Library00/FnList for FnList.java
import Library00.FnA1sz.*;
// Please see Library00/FnA1sz for FnA1sz.java
public class Quiz02_01 {
    public static
	<T extends Comparable<T>>
	FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
	int n = xs.length();
	if (n == 0) return new FnList<Integer>();

	int[] L = new int[n];
	int[] nxt = new int[n];

	for (int i = n - 1; i >= 0; i -= 1) {
	    L[i] = 1;
	    nxt[i] = -1;
	    for (int j = i + 1; j < n; j += 1) {
		if (xs.getAt(j).compareTo(xs.getAt(i)) >= 0) {
		    if (L[j] + 1 > L[i]) {
			L[i] = L[j] + 1;
			nxt[i] = j;
		    }
		}
	    }
	}

	int start = 0;
	for (int i = 1; i < n; i += 1) {
	    if (L[i] > L[start]) start = i;
	}

	FnList<Integer> indices = new FnList<Integer>();
	int idx = start;
	int prevIdx = -1;
	while (idx != -1) {
	    indices = new FnList<Integer>(idx, indices);
	    prevIdx = idx;
	    idx = nxt[idx];
	}
	return reverse(indices);
    }

    private static <T> FnList<T> reverse(FnList<T> xs) {
	FnList<T> ys = new FnList<T>();
	while (xs.consq()) {
	    ys = new FnList<T>(xs.hd(), ys);
	    xs = xs.tl();
	}
	return ys;
    }

    private static <T> void printList(FnList<T> xs) {
	System.out.print("(");
	int i = 0;
	while (xs.consq()) {
	    if (i > 0) System.out.print(",");
	    System.out.print(xs.hd().toString());
	    xs = xs.tl();
	    i += 1;
	}
	System.out.println(")");
    }

    public static void main (String[] args) {
	// Example from the problem statement.
	Integer[] a1 = new Integer[] {1, 2, 1, 2, 3, 1, 2, 3, 4};
	FnList<Integer> r1 =
	    FnA1szLongestMonoSubsequence(new FnA1sz<Integer>(a1));
	System.out.print("Test 1 indices = ");
	printList(r1);
	// expected: (0,1,3,4,7,8)

	Integer[] a2 = new Integer[] {5, 4, 3, 2, 1};
	FnList<Integer> r2 =
	    FnA1szLongestMonoSubsequence(new FnA1sz<Integer>(a2));
	System.out.print("Test 2 (decreasing) indices = ");
	printList(r2);
	// expected: (0)

	Integer[] a3 = new Integer[] {1, 2, 3, 4, 5};
	FnList<Integer> r3 =
	    FnA1szLongestMonoSubsequence(new FnA1sz<Integer>(a3));
	System.out.print("Test 3 (sorted) indices = ");
	printList(r3);
	// expected: (0,1,2,3,4)

	Integer[] a4 = new Integer[] {3, 1, 4, 1, 5, 9, 2, 6};
	FnList<Integer> r4 =
	    FnA1szLongestMonoSubsequence(new FnA1sz<Integer>(a4));
	System.out.print("Test 4 indices = ");
	printList(r4);
	// expected: (1,2,4,5)  -> 1,4,5,9 (length 4)

	FnList<Integer> r5 =
	    FnA1szLongestMonoSubsequence(new FnA1sz<Integer>(new Integer[]{}));
	System.out.print("Test 5 (empty) indices = ");
	printList(r5);
    }
} // end of [public class Quiz02_01{...}]
