/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/

/*
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

import java.util.Random;
import java.util.function.BiConsumer;

public class Final_04 {

    static FnList<FnTupl2<FnList<Character>, Integer>>
	pg2701_word$count$listize4() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the RBST implemented in Quiz02_06 to count the number of
	//    occurrences of each word in the stream of words.
	//    Note that you need to modify your Quiz02_06 implementation to turn
	//    it into an generic associative map for this part.
	// 3. Then figure out a way to turn the RBST-based map into a list WNS
	//    (FnList) of word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize4()
	//
	// HX: Step 2 — Quiz02_06 stored only int keys; the generic map below
	//    (MyRBSTMap) keeps the same node layout / rotations / reroot logic
	//    but parameterizes K (Comparable) and V, and adds put/get/foritm.
	// HX: Step 4 — same merge order as FnListSUtil.mergeSort in Assign05_01;
	//    we reuse Final_02.mergeSort$stackSafe to avoid the O(n) deep
	//    recursion in FnListSUtil.merge.
	//
	// HX: step 1
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	// HX: step 2 — count occurrences in a generic RBST map
	final MyRBSTMap<String, Integer> rbst =
	    new MyRBSTMap<String, Integer>();
	final int[] inserted = {0};
	ws.foritm0(w -> {
	    String k = Final_03.fnList$chars$stringize(w);
	    Integer old = rbst.get(k);
	    if (old == null) {
		rbst.put(k, 1);
		inserted[0] += 1;
		// HX: periodic reroot keeps the tree from degenerating
		//     into a long chain on near-sorted insert sequences.
		if ((inserted[0] & 0x3ff) == 0) rbst.reroot();
	    } else {
		rbst.put(k, old + 1);
	    }
	});
	// HX: step 3 — RBST → FnList of (word, count); order arbitrary
	final FnList<FnTupl2<FnList<Character>, Integer>>[] box =
	    new FnList[]{ FnListSUtil.nil() };
	rbst.foritm((key, n) -> {
	    FnList<Character> w = Final_03.string$fnList(key);
	    box[0] = FnListSUtil.cons(
		new FnTupl2<FnList<Character>, Integer>(w, n),
		box[0]);
	});
	FnList<FnTupl2<FnList<Character>, Integer>> wns =
	    FnListSUtil.reverse(box[0]);
	// HX: step 4–5 — same comparator as Final_02 (count desc, word asc)
	return Final_02.mergeSort$stackSafe(wns, Final_02.pair$cmp);
    }

    private static void print_word(FnList<Character> w) {
	FnList<Character> cur = w;
	while (cur.consq()) {
	    System.out.print(cur.hd()); cur = cur.tl();
	}
    }

    public static void main(String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize4()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	FnList<FnTupl2<FnList<Character>, Integer>> res =
	    pg2701_word$count$listize4();
	int i = 0;
	FnList<FnTupl2<FnList<Character>, Integer>> cur = res;
	while (i < 100 && cur.consq()) {
	    FnTupl2<FnList<Character>, Integer> p = cur.hd();
	    System.out.print((i + 1) + ": ");
	    print_word(p.sub0);
	    System.out.println(" " + p.sub1);
	    cur = cur.tl(); i += 1;
	}
	return;
    }

    /*
     * HX: Quiz02_06 generalized to a randomized BST associative map.
     *
     * Identical strategy to Quiz02_06 (parent links + per-subtree size +
     * rotateLeft/rotateRight + index-based reroot), parameterized over a
     * Comparable key K and an arbitrary value V, with put / get / foritm
     * added so it acts as a map.
     */
    static class MyRBSTMap<K extends Comparable<K>, V> {
	Node root = null;
	private Random rng = new Random();

	class Node {
	    K key; V val;
	    int size;
	    Node parent, lchild, rchild;
	}

	private Node makeNode(K key, V val) {
	    Node n = new Node();
	    n.key = key; n.val = val; n.size = 1;
	    return n;
	}

	private int sizeOf(Node n) {
	    return (n == null) ? 0 : n.size;
	}
	private void recomputeSize(Node n) {
	    if (n != null) n.size = 1 + sizeOf(n.lchild) + sizeOf(n.rchild);
	}

	public int size() { return sizeOf(root); }

	private Node selectByIndex(Node t, int i) {
	    while (true) {
		int leftSize = sizeOf(t.lchild);
		if (i < leftSize) t = t.lchild;
		else if (i == leftSize) return t;
		else { i = i - leftSize - 1; t = t.rchild; }
	    }
	}

	private void rotateRight(Node p) {
	    Node x = p.lchild, b = x.rchild, g = p.parent;
	    x.rchild = p; p.parent = x;
	    p.lchild = b; if (b != null) b.parent = p;
	    x.parent = g;
	    if (g == null) root = x;
	    else if (g.lchild == p) g.lchild = x;
	    else g.rchild = x;
	    recomputeSize(p); recomputeSize(x);
	}

	private void rotateLeft(Node p) {
	    Node x = p.rchild, b = x.lchild, g = p.parent;
	    x.lchild = p; p.parent = x;
	    p.rchild = b; if (b != null) b.parent = p;
	    x.parent = g;
	    if (g == null) root = x;
	    else if (g.lchild == p) g.lchild = x;
	    else g.rchild = x;
	    recomputeSize(p); recomputeSize(x);
	}

	public void reroot() {
	    if (root == null) return;
	    int pick = rng.nextInt(root.size);
	    Node t = selectByIndex(root, pick);
	    while (t.parent != null) {
		Node p = t.parent;
		if (p.lchild == t) rotateRight(p);
		else rotateLeft(p);
	    }
	}

	// HX: associative-map insert. Returns previous value (null if new key).
	public V put(K key, V val) {
	    if (root == null) { root = makeNode(key, val); return null; }
	    Node cur = root, parent = null;
	    int cmp = 0;
	    while (cur != null) {
		parent = cur;
		cmp = key.compareTo(cur.key);
		if (cmp < 0) cur = cur.lchild;
		else if (cmp > 0) cur = cur.rchild;
		else { V old = cur.val; cur.val = val; return old; }
	    }
	    Node fresh = makeNode(key, val);
	    fresh.parent = parent;
	    if (cmp < 0) parent.lchild = fresh; else parent.rchild = fresh;
	    Node up = parent;
	    while (up != null) { up.size += 1; up = up.parent; }
	    return null;
	}

	public V get(K key) {
	    Node cur = root;
	    while (cur != null) {
		int c = key.compareTo(cur.key);
		if (c < 0) cur = cur.lchild;
		else if (c > 0) cur = cur.rchild;
		else return cur.val;
	    }
	    return null;
	}

	// HX: in-order traversal. Iterative to keep call stack O(1) on a
	//    chain that may have ~22k nodes between reroots.
	public void foritm(BiConsumer<? super K, ? super V> work) {
	    Node cur = root;
	    Node[] stack = (Node[]) java.lang.reflect.Array
		.newInstance(Node.class, 64);
	    int top = 0;
	    while (cur != null || top > 0) {
		while (cur != null) {
		    if (top >= stack.length) {
			Node[] ns = (Node[]) java.lang.reflect.Array
			    .newInstance(Node.class, stack.length * 2);
			System.arraycopy(stack, 0, ns, 0, top);
			stack = ns;
		    }
		    stack[top++] = cur; cur = cur.lchild;
		}
		Node n = stack[--top];
		work.accept(n.key, n.val);
		cur = n.rchild;
	    }
	}
    }
}
