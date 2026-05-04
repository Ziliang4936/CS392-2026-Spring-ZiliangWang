package Library00.FnList;

import Library00.FnA1sz.*;
import Library00.LnStrm.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class FnList<T> {
//
    private Node root;
//    
    public final
    FnListSUtil SU = new FnListSUtil();
//
    private class Node {
	T head;
	FnList<T> tail;
	Node(T x0, FnList<T> xs) {
	    head = x0; tail = xs;
	}
    }
//
    public FnList() {
	root = null;
    }
    public FnList(T x0, FnList<T> xs) {
	root = new Node(x0, xs);
    }

    public boolean nilq() {
	return (root == null);
    }
    public boolean consq() {
	return (root != null);
    }

    public T hd() {
	// = hd$raw
	return root.head;
    }
    public FnList<T> tl() {
	// = tl$raw
	return root.tail;
    }
//
    public int length() {
	int res = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public
    FnA1sz<T> toArray() {
	return new FnA1sz(this);
    }
    public
    LnStrm<T> toLnStrm() {
	return SU.strmize(this);
    }
//
    public FnList<T> reverse() {
	return FnListSUtil.reverse(this);
    }
    public FnList<T>
	rappend(FnList<T> ys) {
	return FnListSUtil.rappend(this, ys);
    }
//
    public void System$out$print() {
    	System.out.print("FnList(");
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
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    work.accept(xs.hd());
	    xs = xs.tl();
	}
    }
    public void
	rforitm(Consumer<? super T> work) {
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    work.accept(xs.hd());
	    xs = xs.tl();
	}
    }
    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int i0 = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    work.accept(i0, xs.hd());
	    i0 += 1; xs = xs.tl();
	}
    }
    public void
	irforitm(BiConsumer<Integer, ? super T> work) {
	int i0 = 0;
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    work.accept(i0, xs.hd());
	    i0 += 1; xs = xs.tl();
	}
    }
//
    public boolean
	forall(Predicate<? super T> pred) {
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(xs.hd())) return false;
	    xs = xs.tl();
	}
	return true;
    }
    public boolean
	rforall(Predicate<? super T> pred) {
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(xs.hd())) return false;
	    xs = xs.tl();
	}
	return true;
    }
    public boolean
	iforall(BiPredicate<Integer, ? super T> pred) {
	int i0 = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(i0, xs.hd())) return false;
	    i0 += 1; xs = xs.tl();
	}
	return true;
    }
    public boolean
	irforall(BiPredicate<Integer, ? super T> pred) {
	int i0 = 0;
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(i0, xs.hd())) return false;
	    i0 += 1; xs = xs.tl();
	}
	return true;
    }
//
} // end of [public class FnList<T>{...}]
