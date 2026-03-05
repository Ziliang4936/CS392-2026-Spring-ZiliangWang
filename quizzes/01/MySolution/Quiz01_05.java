//
// HX: 30 points
//
/*
//
 Reverse-stable sorting is similar to stable sorting:
 The ordering of the equals are reversed in the sorted
 version. For instance, 1^1, 2^1, 3^1, 2^2, 3^2, 1^2
 becomes 1^2, 1^1, 2^2, 2^1, 3^2, 3^1 after sorted in
 the reverse-stable manner. If this is unclear to you,
 please seek clarification on Piazza.
//
 No use of external methods (e.g., those from Arrays)
 is allowed here.
//
*/
import MyLibrary.FnList.*;
import java.util.function.ToIntBiFunction;
abstract public class Quiz01_05 {
    public static<T>
	FnList<T> someSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// This one is abstract, that is, not implemented
	// Delegating to FnListSUtil.insertSort (our own insertion sort on FnList).
	return FnListSUtil.insertSort(xs, cmp);
    }

    @SuppressWarnings("unchecked")
    public static<T>
	FnList<T> someRevStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a reverse-stable sorting method
	// based on someSort
	//
	// Tag each element with its original index, then sort
	// with a tie-breaker that orders equal elements by
	// descending index. This guarantees reverse-stability
	// regardless of someSort's own stability properties.

	// 1. Build tagged list: Object[]{element, index}
	FnList<Object[]> tagged = new FnList<>();
	FnList<T> cur = xs;
	int idx = 0;
	while (cur.consq()) {
	    tagged = new FnList<>(new Object[]{cur.hd(), idx}, tagged);
	    idx++;
	    cur = cur.tl();
	}
	tagged = FnListSUtil.reverse(tagged);

	// 2. Sort tagged list: primary key = cmp, tie-break = descending index
	FnList<Object[]> sorted = someSort(tagged, (a, b) -> {
	    int c = cmp.applyAsInt((T) a[0], (T) b[0]);
	    if (c != 0) return c;
	    return Integer.compare((int) b[1], (int) a[1]);
	});

	// 3. Strip tags
	FnList<T> result = new FnList<>();
	FnList<Object[]> cur2 = sorted;
	while (cur2.consq()) {
	    result = new FnList<>((T) cur2.hd()[0], result);
	    cur2 = cur2.tl();
	}
	return FnListSUtil.reverse(result);
    }
}

////////////////////////////////////////////////////////////////////////.
//
// HX-2026-03-04:
//
// Please find a way to test someRevStableSort by
// implementing someSort as insertion-sort on FnList
// and then use someReStableSort to parity-sort the following
// list of 1K integers:
// 0, 1, 2, 3, 4, ..., 999
//
// Your testing code should be inside Quiz01_05_test.java!
//
// Note that you should not add a 'main' method into Quiz01_05
// directly; instead, try to create another class to test Quiz01_05
//
// Note that you should be able to call the insertion sort
// you did (Assign05_01); should not do another implementation of it
//
////////////////////////////////////////////////////////////////////////.
