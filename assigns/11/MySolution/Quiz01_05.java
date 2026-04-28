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
	return FnListSUtil.insertSort(xs, cmp);
    }
    public static<T>
	FnList<T> someRevStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a reverse-stable sorting method
	// based on someSort
	//
	// Build tagged elements (value, originalIndex), then sort by:
	// 1) cmp(value1, value2)
	// 2) for ties, descending originalIndex
	// This enforces reverse-stable order regardless of whether
	// someSort itself is stable.
	FnList<Object[]> tagged = new FnList<Object[]>();
	FnList<T> ys = xs;
	int idx = 0;
	while (ys.consq()) {
	    tagged = new FnList<Object[]>(new Object[]{ys.hd(), idx}, tagged);
	    ys = ys.tl(); idx += 1;
	}
	tagged = FnListSUtil.reverse(tagged);
	@SuppressWarnings("unchecked")
	FnList<Object[]> sorted =
	someSort
	( tagged
	, (a, b) -> {
	      int c = cmp.applyAsInt((T)a[0], (T)b[0]);
	      if (c != 0) return c;
	      return Integer.compare((Integer)b[1], (Integer)a[1]);
	  }
	);
	FnList<T> res = new FnList<T>();
	while (sorted.consq()) {
	    @SuppressWarnings("unchecked")
	    T x0 = (T)sorted.hd()[0];
	    res = new FnList<T>(x0, res);
	    sorted = sorted.tl();
	}
	return FnListSUtil.reverse(res);
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
