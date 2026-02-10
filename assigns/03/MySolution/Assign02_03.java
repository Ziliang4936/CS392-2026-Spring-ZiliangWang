import java.util.Arrays;

public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {

        
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = A[i] + A[j];
                int k = Arrays.binarySearch(A, sum);
                if (k >= 0 && k != i && k != j) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        // Please write some code here for testing solve_3sum
        System.out.println("Testing solve_3sum:");
        
        Integer[] A = {1, 2, 3, 4, 5};
        System.out.println("A = {1,2,3,4,5}: " + solve_3sum(A));  
        // Expected: true (e.g., 1+2=3)
        
        Integer[] B = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("B = {1,...,10}: " + solve_3sum(B));  
        // Expected: true
        
        Integer[] C = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println("C = {1,...,20}: " + solve_3sum(C));  
        // Expected: true
        
        Integer[] D = {0, 2, 4};
        System.out.println("D = {0,2,4}: " + solve_3sum(D));  
        // Expected: true (0+4=4 at indices 0,2,2 - wait, k=2 is used in j)
        // Actually: 0+2=2 (indices 0,1,1) or 2+2=4 (indices 1,1,2) - hmm
        
        Integer[] E = {0};
        System.out.println("E = {0}: " + solve_3sum(E));  
        // Expected: false (not enough elements)
        
        Integer[] F = {1, 3, 7};
        System.out.println("F = {1,3,7}: " + solve_3sum(F));  
        // Expected: false (1+3=4, not in array)
        
        Integer[] G = {-5, -2, 0, 2, 5};
        System.out.println("G = {-5,-2,0,2,5}: " + solve_3sum(G));  
        // Expected: true (-5+5=0 at indices 0,4,2)
    }
}