public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?
	//
	// The array A is sorted. For each target index k, use two pointers
	// to search for A[i] + A[j] == A[k]. If either pointer reaches k,
	// move it away so that i, j, and k are distinct. The outer loop is
	// O(n), and each two-pointer scan is O(n), so the total time is O(n^2).

	int n = A.length;
	if (n < 3) return false;

	for (int k = 0; k < n; k++) {
	    int i = 0;
	    int j = n - 1;
	    while (i < j) {
		if (i == k) {
		    i += 1;
		    continue;
		}
		if (j == k) {
		    j -= 1;
		    continue;
		}
		int sum = A[i] + A[j];
		if (sum == A[k]) {
		    return true;
		} else if (sum < A[k]) {
		    i += 1;
		} else {
		    j -= 1;
		}
	    }
	}
	return false;
    }

    public static void main(String[] args) {
	// Please write some code here for testing solve_3sum
	System.out.println("Testing solve_3sum (O(n^2) two-pointer):");

	// 1+2=3 -> true
	System.out.println("A = {1,2,3,4,5}: " + solve_3sum(new Integer[]{1, 2, 3, 4, 5}));   // true

	// 1+2=3 -> true
	System.out.println("B = {1,...,10}: " + solve_3sum(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // true

	// 1+2=3 -> true
	System.out.println("C = {1,...,20}: " + solve_3sum(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})); // true

	// 0+2=2 -> true (indices 0,1,1) but k must be distinct! 0+4=4 -> (0,2,2) k=j. 2+2=4 -> (1,2,2) k=j.
	// 0+2=2: need k where A[k]=2, k!=0, k!=1. Only index 1 has 2, so no. 0+4=4: need k where A[k]=4, k!=0, k!=2. Index 2 has 4. So k=2, i=0, j=2... but j=2. So we need i<j. i=0, j=2: sum=4. k=2. But k cannot equal j! So no.
	// 2+2=4: i=1, j=2, sum=4. k must have A[k]=4. Index 2 has 4. k=2. But k=j (2). So no.
	// Actually for D={0,2,4}: distinct i,j,k. 0+2=2 -> need k s.t. A[k]=2, k!=0, k!=1. Index 1 has 2. So k=1. But then i=0, j=1, k=1. j=k! So invalid.
	// 0+4=4 -> i=0, j=2, sum=4. k with A[k]=4, k!=0, k!=2. Only index 2 has 4. So k=2, but k=j. Invalid.
	// 2+2=4 -> i=1, j=2, sum=4. k with A[k]=4. Index 2. k=2, j=2. Invalid.
	// So D should be false!
	System.out.println("D = {0,2,4}: " + solve_3sum(new Integer[]{0, 2, 4}));   // false (no distinct i,j,k)

	// not enough elements
	System.out.println("E = {0}: " + solve_3sum(new Integer[]{0}));   // false

	// 1+3=4, not in array
	System.out.println("F = {1,3,7}: " + solve_3sum(new Integer[]{1, 3, 7}));   // false

	// -5+5=0 at indices 0,4,2 -> distinct
	System.out.println("G = {-5,-2,0,2,5}: " + solve_3sum(new Integer[]{-5, -2, 0, 2, 5}));   // true

	// 0+0=0: need k with A[k]=0, k!=0, k!=1. Index 2 has 0. So k=2, i=0, j=1. Valid.
	System.out.println("H = {0,0,0}: " + solve_3sum(new Integer[]{0, 0, 0}));   // true

	// The input is assumed to be sorted by the assignment.
	System.out.println("I = {-10,-4,-1,0,3,4}: " + solve_3sum(new Integer[]{-10, -4, -1, 0, 3, 4})); // true (-4+3=-1)
    }
}
