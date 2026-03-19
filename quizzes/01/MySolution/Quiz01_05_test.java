//
// HX: For testing Quiz01_05
//
import MyLibrary.FnList.*;
import java.util.function.ToIntBiFunction;
abstract public class Quiz01_05_test {
    private static void check(String name, boolean ok) {
	System.out.println((ok ? "[PASS] " : "[FAIL] ") + name);
    }
    private static boolean eqIntArray(FnList<int[]> xs, int[][] exp) {
	int i = 0;
	while (xs.consq() && i < exp.length) {
	    int[] x = xs.hd();
	    if (x[0] != exp[i][0] || x[1] != exp[i][1]) return false;
	    xs = xs.tl(); i += 1;
	}
	return (i == exp.length && xs.nilq());
    }
    private static Integer at(FnList<Integer> xs, int idx) {
	for (int i = 0; i < idx; i++) xs = xs.tl();
	return xs.hd();
    }
    public static void main (String args[]) {
	// Your testing code for Quiz01_05
	//
	// someSort uses FnListSUtil.insertSort for testing.
	// someRevStableSort itself does not rely on stability of someSort:
	// it tags elements with indices and uses descending-index tie-breaking.

	// --- Small reverse-stability check ---
	// Input: [1^1, 2^1, 3^1, 2^2, 3^2, 1^2]
	// Reverse-stable expected: [1^2, 1^1, 2^2, 2^1, 3^2, 3^1]
	ToIntBiFunction<int[], int[]> tagCmp = (a, b) -> Integer.compare(a[0], b[0]);
	FnList<int[]> small = new FnList<>();
	small = new FnList<>(new int[]{1,2}, small);
	small = new FnList<>(new int[]{3,2}, small);
	small = new FnList<>(new int[]{2,2}, small);
	small = new FnList<>(new int[]{3,1}, small);
	small = new FnList<>(new int[]{2,1}, small);
	small = new FnList<>(new int[]{1,1}, small);
	// small = [1^1, 2^1, 3^1, 2^2, 3^2, 1^2]

	FnList<int[]> smallResult = Quiz01_05.someRevStableSort(small, tagCmp);
	System.out.print("Small rev-stable: ");
	smallResult.foritm(x -> System.out.print(x[0] + "^" + x[1] + " "));
	System.out.println(); // expect 1^2 1^1 2^2 2^1 3^2 3^1
	check("small reverse-stable order",
	    eqIntArray(smallResult, new int[][]{
		{1,2}, {1,1}, {2,2}, {2,1}, {3,2}, {3,1}
	    })
	);

	// --- Parity-sort of [0, 1, 2, ..., 999] ---
	// Comparator: even(0) < odd(1).
	// Reverse-stable: among equal-parity elements, original order is reversed.
	//   evens reversed: 998, 996, ..., 2, 0
	//   odds  reversed: 999, 997, ..., 3, 1
	ToIntBiFunction<Integer, Integer> parityCmp =
	    (a, b) -> Integer.compare(a % 2, b % 2);

	FnList<Integer> xs = new FnList<>();
	for (int i = 999; i >= 0; i--) xs = new FnList<>(i, xs);

	FnList<Integer> result = Quiz01_05.someRevStableSort(xs, parityCmp);

	System.out.println("Length: " + result.length()); // expect 1000
	check("length == 1000", result.length() == 1000);

	System.out.print("First 5: ");
	FnList<Integer> tmp = result;
	for (int i = 0; i < 5; i++) {
	    System.out.print(tmp.hd() + " "); tmp = tmp.tl();
	}
	System.out.println(); // expect 998 996 994 992 990

	check("first five == 998 996 994 992 990",
	    at(result, 0) == 998 &&
	    at(result, 1) == 996 &&
	    at(result, 2) == 994 &&
	    at(result, 3) == 992 &&
	    at(result, 4) == 990
	);
	System.out.println("result[499] = " + at(result, 499)); // expect 0
	System.out.println("result[500] = " + at(result, 500)); // expect 999
	System.out.println("result[999] = " + at(result, 999)); // expect 1
	check("boundary indices",
	    at(result, 499) == 0 && at(result, 500) == 999 && at(result, 999) == 1
	);
    }
}
