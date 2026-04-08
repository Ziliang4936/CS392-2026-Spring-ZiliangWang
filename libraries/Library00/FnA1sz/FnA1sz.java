package Library00.FnA1sz;

import Library00.FnList.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnA1sz<T> {
//
    T[] root;
//    
    public final
    FnA1szSUtil SU = new FnA1szSUtil();
//
    public FnA1sz(T[] xs) { root = xs; }
    public FnA1sz(FnList<T> xs) {
	int i = 0;
	int n = xs.length();
	root = (T[])(new Object[n]);
	while (!xs.nilq()) {
	    root[i] = xs.hd(); i++; xs = xs.tl();
	}
    }
//
    public T getAt(int i) {
	return root[i];
    }
//
    public int length() {
	return root.length;
    }
//
    public void System$out$print() {
    	System.out.print("FnA1sz(");
	this.iforitm
	(
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
    public void
	foritm(Consumer<? super T> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(root[i]);
	}
    }
    public void
	rforitm(Consumer<? super T> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(root[n-1-i]);
	}
    }
    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(i, root[i]);
	}
    }
    public void
	irforitm(BiConsumer<Integer, ? super T> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(i, root[n-1-i]);
	}
    }
//
    public boolean
	forall(Predicate<? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(root[i])) return false;
	}
	return true;
    }
    public boolean
	rforall(Predicate<? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(root[n-1-i])) return false;
	}
	return true;
    }
    public boolean
	iforall(BiPredicate<Integer, ? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(i, root[i])) return false;
	}
	return true;
    }
    public boolean
	irforall(BiPredicate<Integer, ? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(i, root[n-1-i])) return false;
	}
	return true;
    }
//
    public FnList<T> listize() {
	FnList<T> res = new FnList<>();
	int n = root.length;
	for (int i = n - 1; i >= 0; i--) {
	    res = new FnList<>(root[i], res);
	}
	return res;
    }
    public FnList<T> rlistize() {
	FnList<T> res = new FnList<>();
	int n = root.length;
	for (int i = 0; i < n; i++) {
	    res = new FnList<>(root[i], res);
	}
	return res;
    }
//
} // end of [public class FnA1sz<T>{...}]
