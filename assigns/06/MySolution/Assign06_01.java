/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) {
	// Please implement standard array-based quickSort and make sure
	// that equal elements are properly handled. In particular, your
	// testing code should test your implementation on an array of 1M zeros!
	if (A == null || A.length <= 1) return;
	quickSort3Way(A, 0, A.length - 1, cmp);
    }

    private static <T> void quickSort3Way(T[] A, int lo, int hi, ToIntBiFunction<T,T> cmp) {
	if (lo >= hi) return;

	// Deterministic pivot (middle element) to avoid external random dependency.
	int pidx = lo + (hi - lo) / 2;
	swap(A, lo, pidx);
	T pivot = A[lo];

	// Dutch National Flag partition:
	// A[lo..lt-1] < pivot, A[lt..gt] == pivot, A[gt+1..hi] > pivot
	int lt = lo;
	int i = lo + 1;
	int gt = hi;
	while (i <= gt) {
	    int sgn = cmp.applyAsInt(A[i], pivot);
	    if (sgn < 0) {
		swap(A, lt, i);
		lt += 1;
		i += 1;
	    } else if (sgn > 0) {
		swap(A, i, gt);
		gt -= 1;
	    } else {
		i += 1;
	    }
	}

	quickSort3Way(A, lo, lt - 1, cmp);
	quickSort3Way(A, gt + 1, hi, cmp);
    }

    private static <T> void swap(T[] A, int i, int j) {
	if (i == j) return;
	T tmp = A[i];
	A[i] = A[j];
	A[j] = tmp;
    }

    private static <T> boolean isSorted(T[] A, ToIntBiFunction<T,T> cmp) {
	for (int i = 1; i < A.length; i += 1) {
	    if (cmp.applyAsInt(A[i-1], A[i]) > 0) return false;
	}
	return true;
    }

    public static void main(String[] args) {
	ToIntBiFunction<Integer,Integer> icmp = (x, y) -> Integer.compare(x, y);

	// Test 1: normal mixed array
	Integer[] A1 = {5, 3, 8, 1, 4, 7, 2, 6};
	arrayQuickSort(A1, icmp);
	System.out.println("Test1 sorted = " + isSorted(A1, icmp)); // expect true

	// Test 2: many equal elements
	Integer[] A2 = {4, 4, 4, 2, 2, 2, 9, 9, 1, 1, 1, 1};
	arrayQuickSort(A2, icmp);
	System.out.println("Test2 sorted = " + isSorted(A2, icmp)); // expect true

	// Test 3 (required): 1M zeros
	Integer[] Z = new Integer[1_000_000];
	for (int i = 0; i < Z.length; i += 1) Z[i] = 0;
	long t0 = System.currentTimeMillis();
	arrayQuickSort(Z, icmp);
	long t1 = System.currentTimeMillis();
	System.out.println("Test3 1M zeros sorted = " + isSorted(Z, icmp)); // expect true
	System.out.println("Test3 time(ms) = " + (t1 - t0));
    }

} // end of [public class Assign06_01{...}]
