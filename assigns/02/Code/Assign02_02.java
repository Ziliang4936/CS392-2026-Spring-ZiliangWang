import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */
    public static <T extends Comparable<T>> int indexOf(T[] a, T key) {
        return indexOfHelper(a, key, 0, a.length - 1);
    }

    private static < T extends Comparable <T>> int indexOfHelper(T[] a, T key, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }


	// Please give a recursive implementation of 'indexOf' that is
	// equivalent to the above one int mid = lo + (hi - lo) / 2;
        int mid = lo + (hi - lo) / 2;
        int sign = key.compareTo(a[mid]);
        
        if (sign < 0) {
            // key < a[mid]，search the left half
            return indexOfHelper(a, key, lo, mid - 1);
        } else if (sign > 0) {
            // key > a[mid]，search the right half
            return indexOfHelper(a, key, mid + 1, hi);
        } else {
            // sign == 0，we find the key
            return mid;
        }
    }
    public static void main(String[] argv) {
	// Please write some testing code for your implementation of 'indexOf'
        // Test code
        Integer[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90};

        System.out.println("Testing indexOf:");
        System.out.println("indexOf(30) = " + indexOf(numbers, 30));  // 2
        System.out.println("indexOf(10) = " + indexOf(numbers, 10));  // 0
        System.out.println("indexOf(90) = " + indexOf(numbers, 90));  // 8
        System.out.println("indexOf(25) = " + indexOf(numbers, 25));  // (not found)
        System.out.println("indexOf(100) = " + indexOf(numbers, 100));

        // Test String array
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nTesting with strings:");
        System.out.println("indexOf(\"cherry\") = " + indexOf(words, "cherry"));  // 2
        System.out.println("indexOf(\"zebra\") = " + indexOf(words, "zebra"));    // -1
    }
}
