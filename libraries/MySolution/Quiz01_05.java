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
package MySolution;

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
	// Implement reverse-stable sorting based on someSort.
	//
	// Strategy: tag each element with its original index,
	// then sort using a comparator that breaks ties by
	// descending original index. This guarantees reverse-
	// stable order regardless of which sorting algorithm
	// someSort happens to use (stable, unstable, any).
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
