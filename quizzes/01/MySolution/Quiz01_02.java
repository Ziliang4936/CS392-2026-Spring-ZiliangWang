public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation
	//
	// Complexity: Sort a copy of the array in O(n^2). For each of
	// the O(n^2) pairs (i,j), binary-search for the product in
	// O(log n), and count occurrences via two binary searches in
	// O(log n). Total: O(n^2 log n) = soft O(n^2).
	int n = A.length;
	if (n < 3) return false;

	Integer[] sorted = new Integer[n];
	for (int i = 0; i < n; i++) sorted[i] = A[i];
	isort(sorted);

	for (int i = 0; i < n; i++) {
	    for (int j = i + 1; j < n; j++) {
		long prod = (long) A[i] * A[j];
		if (prod < Integer.MIN_VALUE || prod > Integer.MAX_VALUE) continue;
		int p = (int) prod;
		int cnt = countIn(sorted, p);
		if (A[i].equals(p)) cnt--;
		if (A[j].equals(p)) cnt--;
		if (cnt >= 1) return true;
	    }
	}
	return false;
    }

    private static void isort(Integer[] A) {
	for (int i = 1; i < A.length; i++) {
	    Integer key = A[i];
	    int j = i - 1;
	    while (j >= 0 && A[j].compareTo(key) > 0) {
		A[j + 1] = A[j]; j--;
	    }
	    A[j + 1] = key;
	}
    }

    private static int countIn(Integer[] sorted, int val) {
	int lo = loBound(sorted, val);
	if (lo < 0) return 0;
	return hiBound(sorted, val) - lo + 1;
    }

    private static int loBound(Integer[] A, int val) {
	int lo = 0, hi = A.length - 1, res = -1;
	while (lo <= hi) {
	    int m = lo + (hi - lo) / 2;
	    if (A[m] == val) { res = m; hi = m - 1; }
	    else if (A[m] < val) lo = m + 1;
	    else hi = m - 1;
	}
	return res;
    }

    private static int hiBound(Integer[] A, int val) {
	int lo = 0, hi = A.length - 1, res = -1;
	while (lo <= hi) {
	    int m = lo + (hi - lo) / 2;
	    if (A[m] == val) { res = m; lo = m + 1; }
	    else if (A[m] < val) lo = m + 1;
	    else hi = m - 1;
	}
	return res;
    }

    public static void main(String[] argv) {
	// Please write some code here for testing solve_3prod
	System.out.println(solve_3prod(new Integer[]{2, 3, 6}));           // true  (2*3=6)
	System.out.println(solve_3prod(new Integer[]{1, 2, 3}));           // false
	System.out.println(solve_3prod(new Integer[]{0, 0, 5}));           // true  (0*5=0, k=other 0)
	System.out.println(solve_3prod(new Integer[]{0, 5, 3}));           // false
	System.out.println(solve_3prod(new Integer[]{-2, -3, 6}));         // true  ((-2)*(-3)=6)
	System.out.println(solve_3prod(new Integer[]{1, 1, 1}));           // true  (1*1=1)
	System.out.println(solve_3prod(new Integer[]{1, 1, 2}));           // false
	System.out.println(solve_3prod(new Integer[]{100000, 100000, 7})); // false (overflow)
    }
}
