package Library00.FnA1sz;

import Library00.FnList.*;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnA1szSUtil {
//
    public static<T>
	FnA1sz<T>
	list$make(FnList<T> xs) {
	return new FnA1sz<T>(xs);
    }
    public static<T>
	FnA1sz<Integer>
	int1$make(int n0) {
	return
	list$make(FnListSUtil.int1$make(n0));
    }
//
    public static<T>
	void System$out$print(FnA1sz<T> xs) {
    	System.out.print("FnA1sz(");
	FnA1szSUtil.iforitm
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
    public static<T,R>
	FnA1sz<R> map_array
	(FnA1sz<T> xs, Function<? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(xs.getAt(i));
	}
	return new FnA1sz<R>(res);
    }
    public static<T,R>
	FnA1sz<R> rmap_array
	(FnA1sz<T> xs, Function<? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(xs.getAt(n-1-i));
	}
	return new FnA1sz<R>(res);
    }
    public static<T,R>
	FnA1sz<R> imap_array
	(FnA1sz<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(i, xs.getAt(i));
	}
	return new FnA1sz<R>(res);
    }
    public static<T,R>
	FnA1sz<R> irmap_array
	(FnA1sz<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(i, xs.getAt(n-1-i));
	}
	return new FnA1sz<R>(res);
    }
//
    public static<T>
	void foritm
	(FnA1sz<T> xs, Consumer<? super T> work) {
	xs.foritm(work); return;
    }
    public static<T>
	void rforitm
	(FnA1sz<T> xs, Consumer<? super T> work) {
	xs.rforitm(work); return;
    }
    public static<T>
	void iforitm
	(FnA1sz<T> xs, BiConsumer<Integer, ? super T> work) {
	xs.iforitm(work); return;
    }
    public static<T>
	void irforitm
	(FnA1sz<T> xs, BiConsumer<Integer, ? super T> work) {
	xs.iforitm(work); return;
    }
//
    public static<T,R>
	R folditm
	(FnA1sz<T> xs, R r0, BiFunction<R, ? super T, R> fopr) {
	R res = r0;
	int n = xs.length();
	for (int i = 0; i < n; i += 1) {
	    res = fopr.apply(res, xs.getAt(i));
	}
	return res;
    }
//
    public static<T,R>
	R rfolditm
	(FnA1sz<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
	R res = r0;
	int n = xs.length();
	for (int i = 0; i < n; i += 1) {
	    res = fopr.apply(xs.getAt(n-1-i), res);
	}
	return res;
    }
//
    public static<T>
	FnA1sz<T> toA1sz(FnA1sz<T> xs) {
	return xs; // HX: This is just a no-op!
    }
//
} // end of [public class FnA1szSUtil{...}]
