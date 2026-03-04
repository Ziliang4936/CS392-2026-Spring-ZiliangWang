import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T> FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// While-loop based insertion sort (no recursion).
	FnList<T> sorted = new FnList<T>();
	while (!xs.nilq()) {
	    T x0 = xs.hd();
	    xs = xs.tl();
	    // Insert x0 into the correct position in sorted
	    FnList<T> prefix = new FnList<T>();
	    FnList<T> rest = sorted;
	    while (!rest.nilq()) {
		int sgn = cmp.applyAsInt(x0, rest.hd());
		if (sgn <= 0) break;
		prefix = new FnList<T>(rest.hd(), prefix);
		rest = rest.tl();
	    }
	    // Rebuild: reverse(prefix) ++ [x0] ++ rest
	    FnList<T> result = new FnList<T>(x0, rest);
	    while (!prefix.nilq()) {
		result = new FnList<T>(prefix.hd(), result);
		prefix = prefix.tl();
	    }
	    sorted = result;
	}
	return sorted;
    }

    public static void main(String[] args) {
	// Small test to verify correctness
	FnList<Integer> small = new FnList<>();
	for (int i = 8; i >= 0; i -= 2) {
	    small = new FnList<>(i, small);
	    small = new FnList<>(i+1, small);
	}
	System.out.print("Before: ");
	FnListSUtil.System$out$print(small);
	System.out.println();
	FnList<Integer> sorted = insertSort(small);
	System.out.print("After:  ");
	FnListSUtil.System$out$print(sorted);
	System.out.println();

	// Build the 1M list: 1, 0, 3, 2, 5, 4, ..., 999999, 999998
	System.out.println("Building 1M list...");
	FnList<Integer> xs = new FnList<>();
	for (int i = 999998; i >= 0; i -= 2) {
	    xs = new FnList<>(i, xs);
	    xs = new FnList<>(i+1, xs);
	}
	System.out.println("List built. Length = " + xs.length());
	System.out.println("Sorting 1M list (this may take a while)...");
	long t0 = System.currentTimeMillis();
	FnList<Integer> ys = insertSort(xs);
	long t1 = System.currentTimeMillis();
	System.out.println("Sort done in " + (t1 - t0) + " ms");
    }

} // end of [public class Assign05_01{...}]
