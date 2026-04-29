//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import Library00.LnStrm.*;
//
public class Quiz02_04 {
    public class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you have already
    // visited each node once.
    //
    // Strategy:
    //   isAVL is implemented by a single post-order
    //   pass that returns the height of the subtree
    //   when it satisfies the AVL property and -1
    //   otherwise. Each node is visited exactly once
    //   so the running time is linear in the size of
    //   the tree (eligible for the bonus).
    //
    public static boolean isAVL (AVLnode avl) {
	return avlHeight(avl) >= 0;
    }
    private static int avlHeight(AVLnode t) {
	if (t == null) return 0;
	int hl = avlHeight(t.lchild);
	if (hl < 0) return -1;
	int hr = avlHeight(t.rchild);
	if (hr < 0) return -1;
	if (hl - hr > 1 || hr - hl > 1) return -1;
	return 1 + (hl >= hr ? hl : hr);
    }
    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    // Strategy / brief explanation (max-height AVL on N keys):
    //   Let f(h) be the minimum number of nodes contained in
    //   any AVL tree of height h. Then f(0)=0, f(1)=1, and
    //   f(h) = 1 + f(h-1) + f(h-2). The trees that realise this
    //   minimum are the so-called Fibonacci trees.
    //
    //   For an AVL tree to attain height h, it must contain at
    //   least f(h) nodes. Therefore the maximum height that an
    //   AVL tree on N nodes can possibly have is the largest h
    //   with f(h) <= N. With N = 1_000_000 we find
    //     f(28) = 832039 <= 1_000_000 < 1_346_268 = f(29),
    //   so the maximum achievable height is h = 28.
    //
    //   genAVLBST builds, by recursion, an AVL of height exactly
    //   28 with exactly 1_000_000 nodes. At every internal node
    //   it picks heights for the two subtrees so that they differ
    //   by at most 1 and their sizes (which can range between
    //   f(h) and 2^h - 1 for height h) sum to (n - 1). Keys are
    //   assigned in in-order, so the result is also a BST whose
    //   keys are 0, 1, ..., 999999.
    //
    private static long[] fmin;
    static {
	fmin = new long[40];
	fmin[0] = 0;
	fmin[1] = 1;
	for (int i = 2; i < 40; i += 1) {
	    fmin[i] = fmin[i - 1] + fmin[i - 2] + 1;
	}
    }

    private static AVLnode generatedRoot = null;
    private static int generatedHeight = -1;
    private static int generatedSize = -1;

    public static boolean genAVLBST() {
	int N = 1_000_000;
	int h = 0;
	while (h + 1 < fmin.length && fmin[h + 1] <= N) h += 1;
	// h is now the max height achievable with N nodes.
	Quiz02_04 outer = new Quiz02_04();
	int[] keyCounter = new int[]{0};
	AVLnode tree = outer.buildExactAVL(h, N, keyCounter);
	generatedRoot = tree;
	generatedHeight = h;
	generatedSize = N;
	System.out.println("genAVLBST: built AVL with N=" + N
			   + " keys, max height = " + h);
	return true;
    }

    private AVLnode buildExactAVL(int h, int n, int[] keyCounter) {
	if (h == 0) {
	    return null;
	}
	if (h == 1) {
	    AVLnode node = this.new AVLnode();
	    node.key = keyCounter[0];
	    keyCounter[0] += 1;
	    return node;
	}
	long need = (long) n - 1;
	// candidate splits: (hL, hR) in {(h-1,h-1), (h-1,h-2), (h-2,h-1)}
	int[][] heightChoices =
	    new int[][] {{h - 1, h - 1}, {h - 1, h - 2}, {h - 2, h - 1}};
	for (int[] hc : heightChoices) {
	    int hL = hc[0], hR = hc[1];
	    long lLo = fmin[hL], lHi = (1L << hL) - 1L;
	    long rLo = fmin[hR], rHi = (1L << hR) - 1L;
	    long sumLo = lLo + rLo, sumHi = lHi + rHi;
	    if (need < sumLo || need > sumHi) continue;
	    long nL = Math.max(lLo, need - rHi);
	    long nR = need - nL;
	    AVLnode node = this.new AVLnode();
	    node.lchild = buildExactAVL(hL, (int) nL, keyCounter);
	    node.key = keyCounter[0];
	    keyCounter[0] += 1;
	    node.rchild = buildExactAVL(hR, (int) nR, keyCounter);
	    return node;
	}
	throw new RuntimeException("Cannot build AVL with h=" + h + " n=" + n);
    }

    private static int subtreeSize(AVLnode t) {
	if (t == null) return 0;
	return 1 + subtreeSize(t.lchild) + subtreeSize(t.rchild);
    }
    private static boolean isBST(AVLnode t, long lo, long hi) {
	if (t == null) return true;
	if (t.key < lo || t.key > hi) return false;
	return isBST(t.lchild, lo, (long) t.key - 1)
	    && isBST(t.rchild, (long) t.key + 1, hi);
    }

    public static void main (String[] args) {
	// Test isAVL on a small valid AVL: just 1 node.
	Quiz02_04 outer = new Quiz02_04();
	AVLnode leaf = outer.new AVLnode();
	leaf.key = 0;
	System.out.println("isAVL(leaf) = " + isAVL(leaf));

	// Build a small valid AVL by hand:
	//        2
	//       / \
	//      1   3
	AVLnode a = outer.new AVLnode(); a.key = 1;
	AVLnode b = outer.new AVLnode(); b.key = 3;
	AVLnode root = outer.new AVLnode(); root.key = 2;
	root.lchild = a; root.rchild = b;
	System.out.println("isAVL(2-node tree) = " + isAVL(root));

	// Build an unbalanced tree:
	//   1
	//    \
	//     2
	//      \
	//       3
	AVLnode u3 = outer.new AVLnode(); u3.key = 3;
	AVLnode u2 = outer.new AVLnode(); u2.key = 2; u2.rchild = u3;
	AVLnode u1 = outer.new AVLnode(); u1.key = 1; u1.rchild = u2;
	System.out.println("isAVL(unbalanced 1-2-3 chain) = " + isAVL(u1));

	// Generate the 1M-key max-height AVL.
	long t0 = System.currentTimeMillis();
	boolean ok = genAVLBST();
	long t1 = System.currentTimeMillis();
	System.out.println("genAVLBST returned " + ok
			   + " in " + (t1 - t0) + " ms");
	System.out.println("verify: isAVL(generated)    = " + isAVL(generatedRoot));
	System.out.println("verify: subtreeSize         = " + subtreeSize(generatedRoot));
	System.out.println("verify: isBST(0..999999)    = "
			   + isBST(generatedRoot, 0, 999999));
	System.out.println("verify: height of generated = " + avlHeight(generatedRoot));
    }
}
