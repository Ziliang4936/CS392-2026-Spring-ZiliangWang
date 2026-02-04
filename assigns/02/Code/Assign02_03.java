public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?

		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				for (int k = j + 1; k < A.length; k++) {
					if (A[i] + A[j] == A[k]) {
						return true;
					}
				}
			}
		}
		return false;
	}
    
    public static void main(String[] args) {
	// Please write some code here for testing solve_3sum
	Integer[] A = {1, 2, 3, 4, 5};
	System.out.println(solve_3sum(A));
	Integer[] B = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	System.out.println(solve_3sum(B));
	Integer[]C = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	System.out.println(solve_3sum(C));
	Integer [] D = {0 , 2, 4};
	System.out.println(solve_3sum(D));
	Integer [] E = {0};
	System.out.println(solve_3sum(E));
    }
}
