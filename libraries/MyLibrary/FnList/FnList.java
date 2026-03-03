package MyLibrary.FnList;

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
    public FnList() {
	root = null;
    }
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
	return root.head;
    }
    public FnList<T> tl() {
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
} // end of [public class FnList<T>{...}]
