package Library00.LnList;

import Library00.FnList.*;
import Library00.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class LnList<T> {
    Node root;
//
    public final
    LnListSUtil SU = new LnListSUtil();
//    
    private class Node {
	T head;
	Node tail;
	Node(T x0, Node xs) {
	    head = x0; tail = xs;
	}
    }
//
    public LnList() {
	root = null;
    }
    public LnList
	(FnList<T> xs) {
	Node ys = null;
	Node tl = null;
	while (!xs.nilq()) {
	    ys = new Node(xs.hd(), ys);
	    xs = xs.tl();
	}
	root = null;
	while (ys != null) {
	    tl = ys.tail;
	    ys.tail = root; root = ys; ys = tl;
	}
    }
    public LnList
	(FnA1sz<T> xs) {
	T x0 = null;
	Node ys = null;
	int n = xs.length();
	for (int i = n; i > 0; i -= 1) {
	    x0 = xs.getAt(i-1);
	    ys = new Node(x0, ys); 
	}
	root = ys;
    }
//
    public LnList
	(T x0, LnList<T> xs) {
	root = new Node(x0, xs.root);
    }
//
    private LnList(Node xs) {
	root = xs;
    }
//
    public void free() {
	root = null; return;
    }
//
    public boolean nilq1() {
	return (root == null);
    }
    public boolean consq1() {
	return (root != null);
    }
//
    public T hd1() {
	return root.head;
    }
    public LnList<T> tl1() {
        Node tail = root.tail;
	return new LnList(tail);
    }
    public LnList<T> tl0() {
	Node tail = root.tail;
	root = null;
	return new LnList(tail);
    }
//
    public void
	link1(LnList<T> tail) {
	assert(root.tail==null);
	root.tail = tail.root; return /*void*/;
    }
    public LnList<T> unlink1() {
	Node tail = root.tail;
	root.tail = null; return new LnList(tail);
    }
//
    public int length1() {
	int res = 0;
	Node xs = root;
	while (xs != null) {
	    res += 1; xs = xs.tail;
	}
	return res;
    }
//
    public LnList<T>
	append0(LnList<T> ys) {
	append1(ys); return this;
    }
    public void
	append1(LnList<T> ys) {
	Node xs = root;
	if (xs == null) {
	    root = ys.root; return;
	}
	while (xs.tail != null) {
	    xs = xs.tail;
	}
	xs.tail/*null*/ = ys.root; return;
    }
//
    public void reverse1() {
	LnList<T>
	xs = this.reverse0();
	root = xs.root; return;
    }
    public LnList<T> reverse0() {
	Node xs = root;
	Node ys = null;
	Node tl = null; root = null;
	while (xs != null) {
	    tl = xs.tail;
	    xs.tail = ys; ys = xs; xs = tl;
	}
	return new LnList<T>(ys);
    }
//
    public void System$out$print1() {
    	System.out.print("LnList(");
	this.iforitm1
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
	foritm1(Consumer<? super T> work) {
	Node xs = root;
	while (xs != null) {
	    work.accept(xs.head); xs = xs.tail;
	}
	return /*void*/;
    }
    public void
	foritm0(Consumer<? super T> work) {
	foritm1(work); root = null; return /*void*/;
    }
//
    public void
	rforitm1(Consumer<? super T> work) {
	Node xs = root;
	Node ys = null;
	while (xs != null) {
	    ys = new Node(xs.head, ys);
	    xs = xs.tail;
	}
	while (ys != null) {
	    work.accept(ys.head); ys = ys.tail;
	}
	return /*void*/;
    }
    public void
	rforitm0(Consumer<? super T> work) {
	rforitm1(work); root = null; return /*void*/;
    }
//
    public void
	iforitm1
	(BiConsumer<Integer, ? super T> work) {
	int i0 = 0;
	Node xs = root;
	while (xs != null) {
	    work.accept(i0, xs.head); i0 += 1; xs = xs.tail;
	}
	return /*void*/;
    }
    public void
	iforitm0
	(BiConsumer<Integer, ? super T> work) {
	iforitm1(work); root = null; return /*void*/;
    }
//
} // end of [public class LnList<T>{...}]
