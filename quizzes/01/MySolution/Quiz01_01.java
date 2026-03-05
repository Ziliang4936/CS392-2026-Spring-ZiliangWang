//
// HX: 20 points
//
/*
import Library00.FnA1sz.*;
*/
import MyLibrary.FnA1sz.*;
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2026-03-03:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.
	int lo = 0;
	int hi = A.length() - 1;
	int result = -1;
	while (lo <= hi) {
	    int mid = lo + (hi - lo) / 2;
	    if (key.compareTo(A.getAt(mid)) >= 0) {
		result = mid;
		lo = mid + 1;
	    } else {
		hi = mid - 1;
	    }
	}
	return result;
    }
    public static void main (String[] args) {
	// HX-2026-03-04:
	// Please write minimal testing code for FnA1szBinarySearch
	// Should test for cases T = Integer and T = String

	// --- Integer tests ---
	Integer[] intArr = {1, 3, 5, 7, 9};
	FnA1sz<Integer> intA = new FnA1sz<Integer>(intArr);
	System.out.println(FnA1szBinarySearch(intA, 6));  // expect 2 (A[2]=5)
	System.out.println(FnA1szBinarySearch(intA, 5));  // expect 2 (A[2]=5)
	System.out.println(FnA1szBinarySearch(intA, 0));  // expect -1
	System.out.println(FnA1szBinarySearch(intA, 9));  // expect 4 (A[4]=9)
	System.out.println(FnA1szBinarySearch(intA, 10)); // expect 4 (A[4]=9)

	// --- String tests ---
	String[] strArr = {"apple", "banana", "cherry", "date"};
	FnA1sz<String> strA = new FnA1sz<String>(strArr);
	System.out.println(FnA1szBinarySearch(strA, "cat"));      // expect 1
	System.out.println(FnA1szBinarySearch(strA, "apple"));    // expect 0
	System.out.println(FnA1szBinarySearch(strA, "aardvark")); // expect -1
	System.out.println(FnA1szBinarySearch(strA, "zebra"));    // expect 3

	return /*void*/;
    }
}
