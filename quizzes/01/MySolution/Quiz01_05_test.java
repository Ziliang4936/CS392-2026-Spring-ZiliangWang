//
// HX: For testing Quiz01_05
//
import MyLibrary.FnList.*;
import java.util.function.ToIntBiFunction;
abstract public class Quiz01_05_test {
    public static void main (String args[]) {
	// Your testing code for Quiz01_05
	//
	// someSort delegates to Assign05_01.insertSort.
	// someRevStableSort tags elements with indices, sorts with
	// descending-index tie-breaking, then strips tags.

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

	// --- Parity-sort of [0, 1, 2, ..., 999] ---
	// Comparator: even(0) < odd(1).
	// Reverse-stable: among equal-parity elements, original order is reversed.
	//   evens reversed: 998, 996, ..., 2, 0   (indices 0..499)
	//   odds  reversed: 999, 997, ..., 3, 1   (indices 500..999)
	ToIntBiFunction<Integer, Integer> parityCmp =
	    (a, b) -> Integer.compare(a % 2, b % 2);

	FnList<Integer> xs = new FnList<>();
	for (int i = 999; i >= 0; i--) xs = new FnList<>(i, xs);

	FnList<Integer> result = Quiz01_05.someRevStableSort(xs, parityCmp);

	System.out.println("Length: " + result.length()); // expect 1000

	// First 5 (evens reversed from 998)
	System.out.print("First 5: ");
	FnList<Integer> tmp = result;
	for (int i = 0; i < 5; i++) {
	    System.out.print(tmp.hd() + " "); tmp = tmp.tl();
	}
	System.out.println(); // expect 998 996 994 992 990

	// Boundary between evens and odds
	tmp = result;
	for (int i = 0; i < 499; i++) tmp = tmp.tl();
	System.out.println("result[499] = " + tmp.hd()); // expect 0
	tmp = tmp.tl();
	System.out.println("result[500] = " + tmp.hd()); // expect 999

	// Last element
	FnList<Integer> tmp2 = result;
	for (int i = 0; i < 999; i++) tmp2 = tmp2.tl();
	System.out.println("result[999] = " + tmp2.hd()); // expect 1
    }
}
