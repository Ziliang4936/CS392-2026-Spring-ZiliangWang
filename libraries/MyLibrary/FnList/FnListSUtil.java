package MyLibrary.FnList;

import MyLibrary.FnList.*;

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

    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	if (xs.nilq()) {
	    return ys;
	} else {
	    return cons(xs.hd(), append(xs.tl(), ys));
	}
    }

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
	boolean nilq(FnList<T> xs) {
	return xs.nilq();
    }
//
    public static
	FnList<Integer> int1$make(int n0) {
	FnList<Integer> res = nil();
	for (int i = n0 - 1; i >= 0; i -= 1) {
	    res = cons(i, res);
	}
	return res;
    }
//
    public static<T>
	FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (nilq(xs)) {
	    return xs;
	} else {
	    return
	    insertSort_insert(insertSort(xs.tl(), cmp), xs.hd(), cmp);
	}
    }
    private static<T>
	FnList<T>
	insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	if (nilq(xs)) {
	    return cons(x0, xs);
	} else {
	    final T hd = xs.hd();
	    final int sgn = cmp.applyAsInt(x0, hd);
	    if (sgn <= 0) {
		return cons(x0, xs);
	    } else {
		return cons(hd, insertSort_insert(xs.tl(), x0, cmp));
	    }
	}
    }
//
    public static<T>
	FnList<T>
	mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n = length(xs);
	if (n <= 1) return xs;
	int mid = n / 2;
	FnList<T> left = nil();
	FnList<T> right = xs;
	for (int i = 0; i < mid; i += 1) {
	    left = cons(right.hd(), left);
	    right = right.tl();
	}
	left = reverse(left);
	left = mergeSort(left, cmp);
	right = mergeSort(right, cmp);
	return merge(left, right, cmp);
    }
    private static<T>
	FnList<T>
	merge(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T,T> cmp) {
	if (nilq(xs)) return ys;
	if (nilq(ys)) return xs;
	final int sgn = cmp.applyAsInt(xs.hd(), ys.hd());
	if (sgn <= 0) {
	    return cons(xs.hd(), merge(xs.tl(), ys, cmp));
	} else {
	    return cons(ys.hd(), merge(xs, ys.tl(), cmp));
	}
    }
//
} // end of [public class FnListSUtil{...}]
