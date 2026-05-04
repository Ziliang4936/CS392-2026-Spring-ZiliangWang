//
// HX: 50 bonus points
//
// This is just Quiz02_02.
//
// I know that many of you spent some time on
// this question. In case, you can solve this one,
// you receive 50 bonus points. If you cannot solve
// it, you do not lose any points.
//
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to insertion-sort up to 1 million
// elements without recursion. Note that loops are
// a special form of recursion and thus are not allowed
// here.
//
// Attention:
// You are suppose to do insertion-sort. If you do
// bubble-sort, you can receive up to 60%, that is
// 30 points of 50.
//
// Strategy:
//   Standard iterative insertion sort:
//     For i = 1, 2, ..., n - 1:
//       take A[i] (the "key") and shift all earlier
//       elements that are greater than the key one
//       slot to the right; then drop the key into the
//       hole that opens up. No recursion is used.
//   On a "nearly sorted" input (each element at most a
//   constant distance from its sorted position) the
//   inner shifting loop does only O(1) work per pass,
//   so the whole sort runs in O(n) time, which scales
//   comfortably to 10^6 elements.
//
public class Final_06 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	// HX-2026-05-04:
	// A is an array of size at most 1000K.
	// Please implement a sorting algorithm
	// WITHOUT recursion that can effectively
	// sort A.
	int n = A.length;
	for (int i = 1; i < n; i += 1) {
	    T key = A[i];
	    int j = i - 1;
	    while (j >= 0 && A[j].compareTo(key) > 0) {
		A[j + 1] = A[j];
		j -= 1;
	    }
	    A[j + 1] = key;
	}
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] A) {
	for (int i = 1; i < A.length; i += 1) {
	    if (A[i - 1].compareTo(A[i]) > 0) return false;
	}
	return true;
    }

    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for sort1000WithNoRecursion
	//
	// HX: same tests as Quiz02_02 — small / pre-sorted / reverse / nearly-sorted 1M.
	Integer[] a1 = new Integer[] {5, 2, 4, 6, 1, 3};
	sort1000WithNoRecursion(a1);
	System.out.print("Test 1 sorted: ");
	for (int x : a1) System.out.print(x + " ");
	System.out.println();
	System.out.println("Test 1 isSorted = " + isSorted(a1));

	Integer[] a2 = new Integer[] {1, 2, 3, 4, 5};
	sort1000WithNoRecursion(a2);
	System.out.println("Test 2 isSorted = " + isSorted(a2));

	Integer[] a3 = new Integer[] {5, 4, 3, 2, 1};
	sort1000WithNoRecursion(a3);
	System.out.println("Test 3 isSorted = " + isSorted(a3));

	int N = 1_000_000;
	Integer[] a4 = new Integer[N];
	for (int i = 0; i < N; i += 2) {
	    a4[i] = i + 1;
	    if (i + 1 < N) a4[i + 1] = i;
	}
	long t0 = System.currentTimeMillis();
	sort1000WithNoRecursion(a4);
	long t1 = System.currentTimeMillis();
	System.out.println("Test 4 (nearly-sorted, N=" + N + ") isSorted = "
			   + isSorted(a4)
			   + " time(ms) = " + (t1 - t0));
    }
} // end of [public class Final_06{...}]
