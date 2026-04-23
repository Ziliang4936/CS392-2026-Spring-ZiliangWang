package Library00.FnList;

import Library00.FnList.*;
import Library00.FnA1sz.*;
import Library00.LnStrm.*;
import Library00.MyRefer.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
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
	FnList<T> sing(T x0) {
	return cons(x0, nil());
    }
    public static<T>
	FnList<T>
	cons(T x0, FnList<T> xs) {
	return new FnList<T>(x0, xs);
    }
//
    public static<T>
	boolean nilq(FnList<T> xs) {
	return xs.nilq();
    }
    public static<T>
	boolean consq(FnList<T> xs) {
	return xs.consq();
    }
//
    public static<T>
	void System$out$print(FnList<T> xs) {
    	System.out.print("FnList(");
	FnListSUtil.iforitm
        ( xs,
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
    public static
	FnList<Object>
	int0$make(int n0) {
	FnList<Object> xs = nil();
	for (int i0 = n0-1; i0 >= 0; i0 -= 1) {
	    xs = cons(null, xs);
	}
	return xs;
    }
    public static
	FnList<Integer>
	int1$make(int n0) {
	FnList<Integer> xs = nil();
	for (int i0 = n0-1; i0 >= 0; i0 -= 1) {
	    xs = cons(i0, xs);
	}
	return xs;
    }
//
    public static
	FnList<Integer>
	rand$int$make(int n0) {
	Random rand = new Random();
	FnList<Integer> xs = nil();
	for (int i0 = 0; i0 < n0; i0 += 1) {
	    xs = cons(rand.nextInt(), xs);
	}
	return xs;
    }
//
    public static<T>
	LnStrm<T> strmize(FnList<T> xs) {
	return new LnStrm<T>(
          () -> {
	      if (xs.nilq()) {
		  return new LnStcn<T>();
	      } else {
		  return new LnStcn<T>(xs.hd(), strmize(xs.tl()));
	      }
	  }
        );
    }
//
    public static<T,R>
	FnList<R> map_list
	  (FnList<T> xs, Function<? super T, R> fopr) {
	FnList<R> rs = nil();
	while (!nilq(xs)) {
	    rs = cons(fopr.apply(xs.hd()), rs);
	    xs = xs.tl();
	}
	return reverse(rs);
    }
    public static<T,R>
	FnList<R> imap_list
	  (FnList<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int i0 = 0;
	FnList<R> rs = nil();
	while (!nilq(xs)) {
	    rs = cons(fopr.apply(i0, xs.hd()), rs);
	    i0 += 1; xs = xs.tl();
	}
	return reverse(rs);
    }
//
    public static<T,R>
	FnList<R> rmap_list
	  (FnList<T> xs, Function<? super T, R> fopr) {
	return map_list(reverse(xs), fopr);
    }
    public static<T,R>
	FnList<R> irmap_list
	  (FnList<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	return imap_list(reverse(xs), fopr);
    }
//
    public static<T>
	void foritm(FnList<T> xs, Consumer<? super T> work) {
	xs.foritm(work); return;
    }
    public static<T>
	void rforitm(FnList<T> xs, Consumer<? super T> work) {
	reverse(xs).foritm(work); return;
    }
    public static<T>
	void iforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	xs.iforitm(work); return;
    }
    public static<T>
	void irforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	reverse(xs).iforitm(work); return;
    }
//
    public static<T>
	boolean forall(FnList<T> xs, Predicate<? super T> pred) {
	return xs.forall(pred);
    }
    public static<T>
	boolean rforall(FnList<T> xs, Predicate<? super T> pred) {
	return reverse(xs).forall(pred);
    }
    public static<T>
	boolean iforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	return xs.iforall(pred);
    }
    public static<T>
	boolean irforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	return reverse(xs).iforall(pred);
    }
//
    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	return rfolditm(xs, ys, (x1, r1) -> cons(x1, r1));
    }
/*
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> ys;
	ys = nil();
	while (!nilq(xs)) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	return ys;
    }
*/
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> r0 = nil();
	return folditm(xs, r0, (r1, x1) -> cons(x1, r1));
    }
//
    public static<T>
	FnList<T> rappend(FnList<T> xs, FnList<T> ys) {
	return folditm(xs, ys, (r1, x1) -> cons(x1, r1));
    }
//
    public static<T,R>
	R folditm
	(FnList<T> xs, R r0, BiFunction<R, ? super T, R> fopr) {
	R rs = r0;
	while (!nilq(xs)) {
	    rs = fopr.apply(rs, xs.hd());
	    xs = xs.tl();
	}
	return rs;
    }
//
    public static<T,R>
	R rfolditm
	(FnList<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
	return folditm(reverse(xs), r0, (x1, r1) -> fopr.apply(r1, x1));
    }
//
    public static
	<T extends Comparable<T>>
	boolean orderedq(FnList<T> xs) {
	return orderedq(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static
	<T extends Comparable<T>>
	boolean orderedq(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	T x0, x1;
	if (nilq(xs)) return true;
	x0 = xs.hd();
	xs = xs.tl();
	while (!nilq(xs)) {
	    x1 = xs.hd();
	    if (cmp.applyAsInt(x0, x1) > 0) {
		return false;
	    } else {
		x0 = x1; xs = xs.tl();
	    }
	}
	return true; // HX: [xs] is ordered
    }
//
    public static<T>
	FnList<T> fwork$make(Consumer<Consumer<? super T>> fwork) {
	MyRefer<FnList<T>> rf = new MyRefer<FnList<T>>(nil());
	fwork.accept((T x0) -> rf.set$raw(cons(x0, rf.get$raw())));
	return reverse(rf.get$raw());
    }
//
    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    // HX-2025-09-30:
    // Poor non-tail-recursive implementation
    // It is here for demonstration purpose only
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
	    if (sgn <= 0) { // HX: for stableness
		return cons(x0, xs); // [x0] is returned
	    } else {
		return cons(hd, insertSort_insert(xs.tl(), x0, cmp));
	    }
	}
    }
//
/*
    private static<T>
	FnList<T>
	insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	FnList<T> ys = nil();
	while(xs.consq()) {
	    if (cmp(x0, xs.hd()) <= 0) {
		return rappend(ys, cons(x0, xs));
	    } else {
		ys = cons(xs.hd(), ys);
		xs = xs.tl();
	    }
	}
	return cons(x0, ys);
    }
*/
//
    public static<T>
	FnA1sz<T> toArray(FnList<T> xs) {
	return xs.toArray();
    }
//
    public static
	<T extends Comparable<T>>
	FnList<T> mergeSort(FnList<T> xs) {
	return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
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
	if (nilq(xs)) return rappend(zs, ys);
	if (nilq(ys)) return rappend(zs, xs);
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
	while (!nilq(xs)) {
	    x0 = xs.hd(); xs = xs.tl();
	    if (cmp.applyAsInt(x0, pt) <= 0)
		us = cons(x0, us); else vs = cons(x0, vs);
	}
	// HX: pivoting for [xs]
	while (!nilq(ys)) {
	    y0 = ys.hd(); ys = ys.tl();
	    if (cmp.applyAsInt(y0, pt) <= 0)
		us = cons(y0, us); else vs = cons(y0, vs);
	}
	return append(quickSort_rand(us, cmp, rand), cons(pt, quickSort_rand(vs, cmp, rand)));
    }
//
} // end of [public class FnListSUtil{...}]
