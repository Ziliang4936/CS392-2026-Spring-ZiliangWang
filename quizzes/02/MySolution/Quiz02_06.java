//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
// Strategy:
//   The BST stores int keys; every Node holds a parent pointer,
//   left/right children, and the size of the subtree rooted at it.
//
//   reroot():
//     1. pick a random node by drawing a random index in
//        [0, size-1] and finding the corresponding in-order node.
//     2. repeatedly rotate the picked node toward the root using
//        right/left tree rotations: if it is a left child, rotate
//        its parent to the right; if it is a right child, rotate
//        its parent to the left. Each rotation preserves the BST
//        property and updates the affected sizes / parent links.
//     3. when the picked node has no parent, it is the new root.
//
//   insert(key):
//     standard iterative BST insert. Walks down the tree following
//     the BST property, returns false if the key already exists,
//     otherwise links a new leaf in place and bumps the size of
//     every ancestor on the path.
//
import java.util.Random;

public class Quiz02_06 {
    Node root = null;
    private Random rng = new Random();

    public class Node {
	int key;
	int size;
	Node parent;
	Node lchild;
	Node rchild;
    }

    private Node makeNode(int key) {
	Node n = new Node();
	n.key = key;
	n.size = 1;
	return n;
    }

    private static int sizeOf(Node n) {
	return (n == null) ? 0 : n.size;
    }

    private static void recomputeSize(Node n) {
	if (n != null) n.size = 1 + sizeOf(n.lchild) + sizeOf(n.rchild);
    }

    // Find the i-th node (0-indexed) in in-order order.
    private Node selectByIndex(Node t, int i) {
	while (true) {
	    int leftSize = sizeOf(t.lchild);
	    if (i < leftSize) {
		t = t.lchild;
	    } else if (i == leftSize) {
		return t;
	    } else {
		i = i - leftSize - 1;
		t = t.rchild;
	    }
	}
    }

    // Right rotation at p: p's left child x becomes p's parent.
    //       p                 x
    //      / \               / \
    //     x   c    =>       a   p
    //    / \                   / \
    //   a   b                 b   c
    private void rotateRight(Node p) {
	Node x = p.lchild;
	Node b = x.rchild;
	Node g = p.parent;

	x.rchild = p;
	p.parent = x;
	p.lchild = b;
	if (b != null) b.parent = p;

	x.parent = g;
	if (g == null) {
	    root = x;
	} else if (g.lchild == p) {
	    g.lchild = x;
	} else {
	    g.rchild = x;
	}
	recomputeSize(p);
	recomputeSize(x);
    }

    // Left rotation at p: p's right child x becomes p's parent.
    //     p                     x
    //    / \                   / \
    //   a   x       =>        p   c
    //      / \               / \
    //     b   c             a   b
    private void rotateLeft(Node p) {
	Node x = p.rchild;
	Node b = x.lchild;
	Node g = p.parent;

	x.lchild = p;
	p.parent = x;
	p.rchild = b;
	if (b != null) b.parent = p;

	x.parent = g;
	if (g == null) {
	    root = x;
	} else if (g.lchild == p) {
	    g.lchild = x;
	} else {
	    g.rchild = x;
	}
	recomputeSize(p);
	recomputeSize(x);
    }

    public void reroot() {
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
	if (root == null) return;
	int n = root.size;
	int pick = rng.nextInt(n);
	Node t = selectByIndex(root, pick);
	while (t.parent != null) {
	    Node p = t.parent;
	    if (p.lchild == t) {
		rotateRight(p);
	    } else {
		rotateLeft(p);
	    }
	}
    }

    public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does nothing and just returns false.
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
	if (root == null) {
	    root = makeNode(key);
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
	Node fresh = makeNode(key);
	fresh.parent = parent;
	if (key < parent.key) parent.lchild = fresh;
	else                  parent.rchild = fresh;
	while (parent != null) {
	    parent.size += 1;
	    parent = parent.parent;
	}
	return true;
    }

    // ----- testing helpers -----

    private static boolean isBST(Node t, long lo, long hi) {
	if (t == null) return true;
	if (t.key < lo || t.key > hi) return false;
	if (!isBST(t.lchild, lo, (long) t.key - 1)) return false;
	if (!isBST(t.rchild, (long) t.key + 1, hi)) return false;
	return true;
    }

    private static boolean parentLinksOk(Node t) {
	if (t == null) return true;
	if (t.lchild != null && t.lchild.parent != t) return false;
	if (t.rchild != null && t.rchild.parent != t) return false;
	return parentLinksOk(t.lchild) && parentLinksOk(t.rchild);
    }

    private static boolean sizesOk(Node t) {
	if (t == null) return true;
	int s = 1 + sizeOf(t.lchild) + sizeOf(t.rchild);
	if (s != t.size) return false;
	return sizesOk(t.lchild) && sizesOk(t.rchild);
    }

    private static void inOrderPrint(Node t) {
	if (t == null) return;
	inOrderPrint(t.lchild);
	System.out.print(t.key + " ");
	inOrderPrint(t.rchild);
    }

    public static void main (String[] args) {
	Quiz02_06 tree = new Quiz02_06();
	int[] keys = new int[] {5, 2, 8, 1, 4, 7, 9, 3, 6};
	for (int k : keys) {
	    System.out.println("insert(" + k + ") = " + tree.insert(k));
	}
	System.out.println("insert(2) again = " + tree.insert(2));

	System.out.print("in-order: ");
	inOrderPrint(tree.root);
	System.out.println();
	System.out.println("isBST = " + isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE)
			   + " parentLinksOk = " + parentLinksOk(tree.root)
			   + " sizesOk = " + sizesOk(tree.root)
			   + " size = " + tree.root.size);

	for (int trial = 0; trial < 5; trial += 1) {
	    tree.reroot();
	    System.out.println("after reroot: root = " + tree.root.key
			       + " isBST = " + isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE)
			       + " parentLinksOk = " + parentLinksOk(tree.root)
			       + " sizesOk = " + sizesOk(tree.root));
	}

	System.out.print("in-order after reroots: ");
	inOrderPrint(tree.root);
	System.out.println();
    }
}
