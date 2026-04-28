/*
** HX-2026-03-04:
** For Quiz01 of CS392X1, Spring, 2026
*/
//
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
//
public class LnList<T> {
    Node root;
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
	return new LnList<T>(root.tail);
    }
//
    public void link(LnList<T> tail) {
	assert(root.tail==null);
	root.tail = tail.root; return /*void*/;
    }
    public LnList<T> unlink() {
	Node tail = root.tail;
	root.tail = null; return new LnList<T>(tail);
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
