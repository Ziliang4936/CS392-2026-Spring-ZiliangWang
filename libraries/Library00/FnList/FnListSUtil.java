package Library00.FnList;

import Library00.FnList.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
/*
import java.util.function.ToIntBiFunction;
*/

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

} // end of [public class FnListSUtil{...}]
