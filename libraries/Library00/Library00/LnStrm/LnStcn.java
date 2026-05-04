//
package Library00.LnStrm;
//
//
// HX-2025-10-23:
// typedef LStrm<T> = Supplier<LStcn<T>>
//
public class LnStcn<T> {
    private Node root;

    private class Node {
	T head;
	 LnStrm<T> tail;
    }

    public LnStcn() {
	root = null;
    }
    public LnStcn(T hd) {
	root = new Node();
	root.head = hd;
	root.tail = new LnStrm<T>();
    }
    public LnStcn
	(T hd, LnStrm<T> tl) {
	root = new Node();
	root.head = hd; root.tail = tl;
    }

    public T hd() {
	return root.head;
    }
    public LnStrm<T> tl() {
	return root.tail;
    }
    public boolean nilq() {
	return (root == null);
    }
    public boolean consq() {
	return (root != null);
    }
} // end of [class LnStcn<T>{...}]
