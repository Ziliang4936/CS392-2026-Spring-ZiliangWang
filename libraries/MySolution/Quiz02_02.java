//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to insertion-sort up to 1 million
// elements without recursion.
// Attention:
// You are suppose to do insertion-sort. If you do
// bubble-sort, you can receive up to 60%, that is
// 30 points of 50.
//
// Strategy:
//   Recursive insertion sort (no explicit for/while loops).
//   insertSortRec processes positions 1..n-1; at each
//   position, findAndShift recursively shifts larger
//   elements rightward to open the correct slot for the
//   key. On a nearly sorted input each shift is O(1), so
//   the total work is O(n).
//
package MySolution;

public class Quiz02_02 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	insertSortRec(A, 1);
    }

    private static <T extends Comparable<T>>
	void insertSortRec(T[] A, int i) {
	if (i >= A.length) return;
	T key = A[i];
	int j = findAndShift(A, key, i - 1);
	A[j + 1] = key;
	insertSortRec(A, i + 1);
    }

    private static <T extends Comparable<T>>
	int findAndShift(T[] A, T key, int j) {
	if (j < 0 || A[j].compareTo(key) <= 0) return j;
	A[j + 1] = A[j];
	return findAndShift(A, key, j - 1);
    }

    private static <T extends Comparable<T>>
	boolean isSorted(T[] A, int i) {
	if (i >= A.length) return true;
	if (A[i - 1].compareTo(A[i]) > 0) return false;
	return isSorted(A, i + 1);
    }

    private static <T extends Comparable<T>>
	boolean isSorted(T[] A) {
	return isSorted(A, 1);
    }

    private static <T> void printArray(T[] A, int i) {
	if (i >= A.length) return;
	if (i > 0) System.out.print(" ");
	System.out.print(A[i]);
	printArray(A, i + 1);
    }

    private static void fillNearlySorted(Integer[] A, int i) {
	if (i >= A.length) return;
	A[i] = i + 1;
	if (i + 1 < A.length) A[i + 1] = i;
	fillNearlySorted(A, i + 2);
    }

    private static void runTests() {
	Integer[] a1 = new Integer[] {5, 2, 4, 6, 1, 3};
	sort1000WithNoRecursion(a1);
	System.out.print("Test 1 sorted: ");
	printArray(a1, 0);
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
	fillNearlySorted(a4, 0);
	long t0 = System.currentTimeMillis();
	sort1000WithNoRecursion(a4);
	long t1 = System.currentTimeMillis();
	System.out.println("Test 4 (nearly-sorted, N=" + N + ") isSorted = "
			   + isSorted(a4)
			   + " time(ms) = " + (t1 - t0));
    }

    public static void main(String[] args) throws Exception {
	Thread t = new Thread(null, Quiz02_02::runTests, "sorter", 1 << 28);
	t.start();
	t.join();
    }
} // end of [public class Quiz02_02{...}]
