package MyLibrary.FnList;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnList<T> {
//
    private Node root;
    private class Node {
	T head;
	FnList<T> tail;
	Node(T x0, FnList<T> xs) {
	    head = x0; tail = xs;
	}
    }
//
    // HX: Contruct an empty list
    public FnList() {
	root = null;
    }
    // HX: Contruct an non-empty list
    public FnList(T x0, FnList<T> xs) {
	root = new Node(x0, xs);
    }
//
    public boolean nilq() {
	return (root == null);
    }
    public boolean consq() {
	return (root != null);
    }
//
    public T hd() {
	// = hd$raw
	return root.head;
    }
    public FnList<T> tl() {
	// = tl$raw
	return root.tail;
    }
//
    // HX: [length] is O(n)
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
    public void foritm(Consumer<? super T> work) {
	FnList<T> xs = this;
	while (xs.consq()) {
	    work.accept(xs.hd()); xs = xs.tl();
	}
    }
    public void iforitm(BiConsumer<Integer, ? super T> work) {
	int i = 0;
	FnList<T> xs = this;
	while (xs.consq()) {
	    work.accept(i, xs.hd()); i += 1; xs = xs.tl();
	}
    }
//
} // end of [public class FnList<T>{...}]
