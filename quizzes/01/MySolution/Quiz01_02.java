import java.util.HashMap;

public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation
	//
	// Complexity: Build a frequency map in O(n). Iterate over all
	// O(n^2) pairs (i,j); for each pair do an O(1) expected-time
	// HashMap lookup for the product. Total: O(n^2) expected = soft O(n^2).
	// (Overflow: product computed as long; values out of int range skipped.)
	int n = A.length;
	if (n < 3) return false;

	HashMap<Integer, Integer> freq = new HashMap<>();
	for (int i = 0; i < n; i++) {
	    freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
	}

	for (int i = 0; i < n; i++) {
	    for (int j = i + 1; j < n; j++) {
		long prod = (long) A[i] * A[j];
		if (prod < Integer.MIN_VALUE || prod > Integer.MAX_VALUE) continue;
		int p = (int) prod;
		int cnt = freq.getOrDefault(p, 0);
		if (A[i].equals(p)) cnt--;
		if (A[j].equals(p)) cnt--;
		if (cnt >= 1) return true;
	    }
	}
	return false;
    }

    public static void main(String[] argv) {
	// Please write some code here for testing solve_3prod
	// 2*3=6 -> true
	System.out.println(solve_3prod(new Integer[]{2, 3, 6}));         // true
	// no triple product -> false
	System.out.println(solve_3prod(new Integer[]{1, 2, 3}));         // false
	// 0*5=0 (k=1 as the other 0) -> true
	System.out.println(solve_3prod(new Integer[]{0, 0, 5}));         // true
	// only one zero, 0*x=0 but no second 0 -> false
	System.out.println(solve_3prod(new Integer[]{0, 5, 3}));         // false
	// (-2)*(-3)=6 -> true
	System.out.println(solve_3prod(new Integer[]{-2, -3, 6}));       // true
	// 1*1=1 -> use k=2 -> true
	System.out.println(solve_3prod(new Integer[]{1, 1, 1}));         // true
	// 1*1=1 but only two 1s, no third index -> false
	System.out.println(solve_3prod(new Integer[]{1, 1, 2}));         // false
	// large overflow pair: 100000*100000=10^10, out of int range -> false
	System.out.println(solve_3prod(new Integer[]{100000, 100000, 7})); // false
    }
}
