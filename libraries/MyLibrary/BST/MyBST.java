package MyLibrary.BST;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyBST<K extends Comparable<K>, V> {
//
    private Node root;
    private int size;
//
    private class Node {
	K key; V val;
	Node left, right, parent;
	Node(K k, V v, Node p) {
	    key = k; val = v; parent = p;
	    left = null; right = null;
	}
    }
//
    public MyBST() {
	root = null; size = 0;
    }
//
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
//
    private Node findNode(K key) {
	Node n = root;
	while (n != null) {
	    int c = key.compareTo(n.key);
	    if (c == 0) return n;
	    else if (c < 0) n = n.left;
	    else n = n.right;
	}
	return null;
    }
//
    public V search(K key) {
	Node n = findNode(key);
	return (n == null) ? null : n.val;
    }
//
    public V insert(K key, V val) {
	if (root == null) {
	    root = new Node(key, val, null);
	    size++; return null;
	}
	Node n = root;
	while (true) {
	    int c = key.compareTo(n.key);
	    if (c == 0) {
		V old = n.val; n.val = val; return old;
	    } else if (c < 0) {
		if (n.left == null) {
		    n.left = new Node(key, val, n);
		    size++; return null;
		}
		n = n.left;
	    } else {
		if (n.right == null) {
		    n.right = new Node(key, val, n);
		    size++; return null;
		}
		n = n.right;
	    }
	}
    }
//
    public V remove(K key) {
	Node n = findNode(key);
	if (n == null) return null;
	V old = n.val;
	deleteNode(n);
	size--;
	return old;
    }
    private void deleteNode(Node n) {
	if (n.left != null && n.right != null) {
	    Node succ = minNode(n.right);
	    n.key = succ.key;
	    n.val = succ.val;
	    deleteNode(succ);
	    return;
	}
	Node child = (n.left != null) ? n.left : n.right;
	if (child != null) child.parent = n.parent;
	if (n.parent == null) root = child;
	else if (n == n.parent.left) n.parent.left = child;
	else n.parent.right = child;
    }
    private Node minNode(Node n) {
	while (n.left != null) n = n.left;
	return n;
    }
//
    public void foritm(BiConsumer<? super K, ? super V> work) {
	inorder(root, work);
    }
    private void inorder(Node n, BiConsumer<? super K, ? super V> work) {
	if (n == null) return;
	inorder(n.left, work);
	work.accept(n.key, n.val);
	inorder(n.right, work);
    }
//
    public void foritm_pre(BiConsumer<? super K, ? super V> work) {
	preorder(root, work);
    }
    private void preorder(Node n, BiConsumer<? super K, ? super V> work) {
	if (n == null) return;
	work.accept(n.key, n.val);
	preorder(n.left, work);
	preorder(n.right, work);
    }
//
    public void foritm_post(BiConsumer<? super K, ? super V> work) {
	postorder(root, work);
    }
    private void postorder(Node n, BiConsumer<? super K, ? super V> work) {
	if (n == null) return;
	postorder(n.left, work);
	postorder(n.right, work);
	work.accept(n.key, n.val);
    }
//
    public K min$opt() {
	if (root == null) return null;
	return minNode(root).key;
    }
    public K max$opt() {
	if (root == null) return null;
	Node n = root;
	while (n.right != null) n = n.right;
	return n.key;
    }
//
} // end of [public class MyBST<K,V>{...}]
