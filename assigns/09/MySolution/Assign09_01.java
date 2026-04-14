//
// HX-2026-04-09: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 20 points for insert and 30 points for remove
//
public class Assign09_01 {
    Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }
    private Node makeNode(int key, Node parent) {
	Node node = new Node();
	node.key = key;
	node.size = 1;
	node.parent = parent;
	node.lchild = null;
	node.rchild = null;
	return node;
    }
    private int sizeOf(Node node) {
	return (node == null ? 0 : node.size);
    }
    private void fixSizeUpward(Node node) {
	while (node != null) {
	    node.size = 1 + sizeOf(node.lchild) + sizeOf(node.rchild);
	    node = node.parent;
	}
    }
    private Node searchNode(int key) {
	Node cur = root;
	while (cur != null) {
	    if (key < cur.key) {
		cur = cur.lchild;
	    } else if (key > cur.key) {
		cur = cur.rchild;
	    } else {
		return cur;
	    }
	}
	return null;
    }
    private Node leftmost(Node node) {
	while (node.lchild != null) {
	    node = node.lchild;
	}
	return node;
    }
    private void transplant(Node oldn, Node newn) {
	if (oldn.parent == null) {
	    root = newn;
	} else if (oldn.parent.lchild == oldn) {
	    oldn.parent.lchild = newn;
	} else {
	    oldn.parent.rchild = newn;
	}
	if (newn != null) {
	    newn.parent = oldn.parent;
	}
    }
    public boolean insert(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [insert] does nothing and just returns false.
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
	if (root == null) {
	    root = makeNode(key, null);
	    return true;
	}
	Node cur = root;
	Node parent = null;
	while (cur != null) {
	    parent = cur;
	    if (key < cur.key) {
		cur = cur.lchild;
	    } else if (key > cur.key) {
		cur = cur.rchild;
	    } else {
		return false;
	    }
	}
	Node node = makeNode(key, parent);
	if (key < parent.key) {
	    parent.lchild = node;
	} else {
	    parent.rchild = node;
	}
	fixSizeUpward(parent);
	return true;
    }
    public boolean remove(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [remove] removes the key and the new tree
	// obtained is still a binary search tree and
	// [remove] returns true to indicate the removal
	// is done.
	// If key is not in the tree stored at [root],
	// [remove] does nothing and returns false to
	// indicate that no removal of the key k is done.
	Node node = searchNode(key);
	if (node == null) {
	    return false;
	}
	if (node.lchild == null) {
	    Node p = node.parent;
	    transplant(node, node.rchild);
	    fixSizeUpward(p);
	} else if (node.rchild == null) {
	    Node p = node.parent;
	    transplant(node, node.lchild);
	    fixSizeUpward(p);
	} else {
	    Node succ = leftmost(node.rchild);
	    Node succParent0 = succ.parent;
	    if (succ.parent != node) {
		transplant(succ, succ.rchild);
		succ.rchild = node.rchild;
		succ.rchild.parent = succ;
	    }
	    transplant(node, succ);
	    succ.lchild = node.lchild;
	    succ.lchild.parent = succ;
	    succ.size = 1 + sizeOf(succ.lchild) + sizeOf(succ.rchild);
	    if (succParent0 != node) {
		fixSizeUpward(succParent0);
	    }
	    fixSizeUpward(succ);
	}
	return true;
    }
    public static void main (String[] args) {
	// Please add minimal testing code for insert()
	// Please add minimal testing code for remove()
	Assign09_01 t = new Assign09_01();
	System.out.println("insert 5 -> " + t.insert(5));
	System.out.println("insert 3 -> " + t.insert(3));
	System.out.println("insert 7 -> " + t.insert(7));
	System.out.println("insert 6 -> " + t.insert(6));
	System.out.println("insert 8 -> " + t.insert(8));
	System.out.println("insert duplicate 7 -> " + t.insert(7));
	System.out.println("root.size = " + t.root.size);
	System.out.println("remove 3 -> " + t.remove(3));
	System.out.println("remove 7 -> " + t.remove(7));
	System.out.println("remove 42 -> " + t.remove(42));
	System.out.println("root.key = " + t.root.key);
	System.out.println("root.size = " + t.root.size);
	return /*void*/;
    }
}
