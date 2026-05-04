package MySolution;

public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?
	//
	// Sort a copy of A with insertion sort: O(n^2).
	// For each target index k in the sorted copy, run a
	// two-pointer scan (i from left, j from right) to find
	// S[i]+S[j]==S[k] while skipping i==k or j==k.
	// Each scan is O(n), and there are O(n) targets,
	// so the scan phase is O(n^2).
	// Total: O(n^2) which is soft O(n^2).

	int n = A.length;
	if (n < 3) return false;

	Integer[] S = new Integer[n];
	for (int i = 0; i < n; i++) S[i] = A[i];
	isort(S);

	for (int k = 0; k < n; k++) {
	    int i = 0;
	    int j = n - 1;
	    while (i < j) {
		if (i == k) { i++; continue; }
		if (j == k) { j--; continue; }
		long sum = (long) S[i] + S[j];
		if (sum == (long) S[k]) {
		    return true;
		} else if (sum < (long) S[k]) {
		    i++;
		} else {
		    j--;
		}
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

    public static void main(String[] args) {
	System.out.println("Testing solve_3sum (sort + two-pointer, soft O(n^2)):");

	System.out.println("A = {1,2,3,4,5}: " + solve_3sum(new Integer[]{1, 2, 3, 4, 5}));   // true (1+2=3)
	System.out.println("B = {1,...,10}: " + solve_3sum(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // true
	System.out.println("C = {0,2,4}: " + solve_3sum(new Integer[]{0, 2, 4}));   // false (no distinct i,j,k)
	System.out.println("D = {0}: " + solve_3sum(new Integer[]{0}));   // false
	System.out.println("E = {1,3,7}: " + solve_3sum(new Integer[]{1, 3, 7}));   // false
	System.out.println("F = {-5,-2,0,2,5}: " + solve_3sum(new Integer[]{-5, -2, 0, 2, 5}));   // true (-5+5=0)
	System.out.println("G = {0,0,0}: " + solve_3sum(new Integer[]{0, 0, 0}));   // true (0+0=0)
	System.out.println("H = {-10,-4,-1,0,3,4}: " + solve_3sum(new Integer[]{-10, -4, -1, 0, 3, 4})); // true (-4+3=-1)
	System.out.println("I = {5,1,3,2,4}: " + solve_3sum(new Integer[]{5, 1, 3, 2, 4}));   // true (1+3=4, unsorted input)
	System.out.println("J = {7,2,10,5}: " + solve_3sum(new Integer[]{7, 2, 10, 5}));   // true (2+5=7, unsorted input)
    }
}
