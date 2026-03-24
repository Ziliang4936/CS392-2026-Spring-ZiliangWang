package Library00.FnList;

import Library00.FnList.*;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class FnListSUtil {
//
    public static<T>
	FnList<T> nil() {
	return new FnList<T>();
    }
    public static<T>
	FnList<T>
	cons(T x0, FnList<T> xs) {
	return new FnList<T>(x0, xs);
    }
//
    // HX: [length] is O(n)
    public static<T>
	int length(FnList<T> xs) {
	int res = 0;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> ys;
	ys = nil();
	while (!xs.nilq()) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	return ys;
    }    
//
    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	if (xs.nilq()) {
	    return ys;
	} else {
	    return cons(xs.hd(), append(xs.tl(), ys));
	}
    }
//
    public static<T>
	FnList<T> rappend(FnList<T> xs, FnList<T> ys) {
	while (!xs.nilq()) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	return ys;
    }
//
    public static<T>
	void foritm(FnList<T> xs, Consumer<? super T> work) {
	while (xs.consq()) {
	    work.accept(xs.hd()); xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean forall(FnList<T> xs, Predicate<? super T> pred) {
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(xs.hd())) {
		    xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void iforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	int i = 0;
	while (xs.consq()) {
	    work.accept(i, xs.hd()); i += 1; xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean iforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	int i = 0;
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(i, xs.hd())) {
		    i += 1; xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void System$out$print(FnList<T> xs) {
    	System.out.print("FnList(");
	iforitm(xs,
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public static<T>
	FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq()) {
	    return xs;
	} else {
	    return
	    insertSort_insert(insertSort(xs.tl(), cmp), xs.hd(), cmp);
	}
    }
    private static<T>
	FnList<T>
	insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq()) {
	    return cons(x0, xs);
	} else {
	    final T hd = xs.hd();
	    final int sgn = cmp.applyAsInt(x0, hd);
	    if (sgn <= 0) { // HX: for stableness
		return cons(x0, xs); // [x0] is returned
	    } else {
		return cons(hd, insertSort_insert(xs.tl(), x0, cmp));
	    }
	}
    }
//
    public static
	<T extends Comparable<T>>
	FnList<T> mergeSort(FnList<T> xs) {
	return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	FnList<T>
	mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n0 = xs.length();
	if (n0 <= 1) {
	    return xs;
	} else {
	    return mergeSort_split(xs, nil(), n0, 0, cmp);
	}
    }
    private static<T>
	FnList<T> mergeSort_split
	(FnList<T> xs,
	 FnList<T> ys, int n0, int n1, ToIntBiFunction<T,T> cmp) {
	if (2*n1 < n0) {
	    return mergeSort_split
		(xs.tl(), cons(xs.hd(), ys), n0, n1+1, cmp);
	} else {
	    return mergeSort_merge
		(mergeSort(reverse(ys), cmp), mergeSort(xs, cmp), cmp);
	}
    }
    private static<T>
	FnList<T> mergeSort_merge
	(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T,T> cmp) {
	return mergeSort_merge_helper(xs, ys, nil(), cmp);
    }
    private static<T>
	FnList<T> mergeSort_merge_helper
	(FnList<T> xs, FnList<T> ys, FnList<T> zs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq()) return rappend(zs, ys);
	if (ys.nilq()) return rappend(zs, xs);
	if (cmp.applyAsInt(xs.hd(), ys.hd()) <= 0) {
	    return mergeSort_merge_helper(xs.tl(), ys, cons(xs.hd(), zs), cmp);
	} else {
	    return mergeSort_merge_helper(xs, ys.tl(), cons(ys.hd(), zs), cmp);
	}
    }
//
    public static
	<T extends Comparable<T>>
	FnList<T> quickSort(FnList<T> xs) {
	return quickSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	FnList<T>
	quickSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return quickSort_rand(xs, cmp, new Random());
    }
    private static<T>
	FnList<T>
	quickSort_rand
	(FnList<T> xs, ToIntBiFunction<T,T> cmp, Random rand) {
	int n0 = xs.length();
	if (n0 <= 1) return xs;
	// HX:
	// [p0] is randomly chosen for the pivot
	int p0 = rand.nextInt() % n0;
	p0 = (p0 >= 0) ? p0 : (n0+p0);
	//
	// HX:
	// For locating the randomly chosen pivot
	//
	FnList<T> ys = nil();
	for (int i = 0; i < p0; i += 1) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	//
	T x0, y0;
	final T pt = xs.hd(); xs = xs.tl();
	FnList<T> us = nil(); //  those <= pivot
	FnList<T> vs = nil(); //  those >> pivot
	// HX: pivoting for [xs]
	while (!xs.nilq()) {
	    x0 = xs.hd(); xs = xs.tl();
	    if (cmp.applyAsInt(x0, pt) <= 0)
		us = cons(x0, us); else vs = cons(x0, vs);
	}
	// HX: pivoting for [xs]
	while (!ys.nilq()) {
	    y0 = ys.hd(); ys = ys.tl();
	    if (cmp.applyAsInt(y0, pt) <= 0)
		us = cons(y0, us); else vs = cons(y0, vs);
	}
	return append(quickSort_rand(us, cmp, rand), cons(pt, quickSort_rand(vs, cmp, rand)));
    }
//
} // end of [public class FnListSUtil{...}]
