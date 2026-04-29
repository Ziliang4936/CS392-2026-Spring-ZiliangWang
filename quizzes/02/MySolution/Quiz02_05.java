//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
public class Quiz02_05 {
    public class RBTnode {
	int key;
	int color; // Red = 0; Black = 1
	RBTnode lchild;
	RBTnode rchild;
    }
    private static final int RED = 0;
    private static final int BLACK = 1;
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [rbt] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you already visit
    // each node once.
    //
    // Strategy:
    //   isRBT does a single post-order pass returning the
    //   black-height of each subtree (number of black nodes
    //   on every root-to-NIL path, counting NILs as black
    //   and not counting the node itself), or -1 if the
    //   subtree fails any RBT invariant. The invariants
    //   checked are:
    //     - the root is black,
    //     - no red node has a red child (red-red violation),
    //     - both subtrees of every node have the same black
    //       height.
    //   Each node is visited exactly once, so the cost is
    //   linear in the number of nodes (eligible for the bonus).
    //
    public static boolean isRBT (RBTnode rbt) {
	if (rbt == null) return true;
	if (rbt.color != BLACK) return false;
	return rbtBlackHeight(rbt, false) >= 0;
    }
    private static int rbtBlackHeight(RBTnode t, boolean parentIsRed) {
	if (t == null) return 1;
	if (t.color != RED && t.color != BLACK) return -1;
	boolean isRed = (t.color == RED);
	if (parentIsRed && isRed) return -1;
	int hl = rbtBlackHeight(t.lchild, isRed);
	if (hl < 0) return -1;
	int hr = rbtBlackHeight(t.rchild, isRed);
	if (hr < 0) return -1;
	if (hl != hr) return -1;
	return hl + (isRed ? 0 : 1);
    }
    //
    // HX: 20 points
    // This is largely about understanding red-black trees.
    // Please explain BRIEFLY as to why the generated RBT is
    // of minimal black height (not height).
    //
    // Strategy / brief explanation:
    //   The black height of an RBT containing N keys is at
    //   least ceil(log_2(N + 1)) -- the bound is achieved
    //   when every node is black, but the smallest black
    //   height we can hope for is when red and black layers
    //   alternate, doubling the maximum number of nodes per
    //   black-height unit. An RBT whose levels strictly
    //   alternate black, red, black, red, ..., starting from
    //   a black root, can have at most
    //         1 + 2 + 4 + ... + 2^(2b - 1) = 4^b - 1
    //   nodes for black-height b. Therefore the minimum
    //   achievable black-height for N nodes is
    //         b_min = ceil(log_4(N + 1)).
    //   With N = 1_000_000:
    //         4^9  - 1 = 262_143    < 1_000_000
    //         4^10 - 1 = 1_048_575 >= 1_000_000
    //   so the minimum achievable black-height is **b = 10**.
    //
    //   genRedBLackBST builds a tree of the following shape:
    //     - depths 0..18 form a perfect binary tree (524287
    //       nodes); the colour of a node at depth d is BLACK
    //       if d is even and RED if d is odd, so this part
    //       contains 10 black "layers" (depths 0,2,...,18);
    //     - depth 19 holds the remaining 475713 RED nodes,
    //       packed leftmost; their parents at depth 18 are
    //       black so the red-red invariant is fine; their
    //       children are NIL (black).
    //   Every root-to-NIL path therefore traverses exactly the
    //   10 black nodes at depths 0,2,...,18 plus the final NIL,
    //   giving the same black-height of 10 on every path. The
    //   keys 0..999999 are assigned by an in-order walk so the
    //   tree is also a BST.
    //
    private static RBTnode generatedRoot = null;
    private static int generatedSize = -1;
    private static int generatedBlackHeight = -1;

    public static boolean genRedBLackBST() {
	final int N = 1_000_000;
	int b = 1;
	long maxForB = 3L; // 4^1 - 1 = 3
	while (maxForB < N) {
	    b += 1;
	    maxForB = maxForB * 4L + 3L; // 4^b - 1
	}
	// b is now the minimum black-height for N nodes.
	Quiz02_05 outer = new Quiz02_05();
	RBTnode[] arr = new RBTnode[N + 1];
	for (int i = 1; i <= N; i += 1) {
	    arr[i] = outer.new RBTnode();
	    int depth = 31 - Integer.numberOfLeadingZeros(i);
	    arr[i].color = (depth % 2 == 0) ? BLACK : RED;
	}
	for (int i = 1; i <= N; i += 1) {
	    int li = 2 * i, ri = 2 * i + 1;
	    if (li <= N) arr[i].lchild = arr[li];
	    if (ri <= N) arr[i].rchild = arr[ri];
	}
	int[] keyCounter = new int[]{0};
	inOrderAssign(arr[1], keyCounter);
	generatedRoot = arr[1];
	generatedSize = N;
	generatedBlackHeight = b;
	System.out.println("genRedBLackBST: built RBT with N=" + N
			   + " keys, min black-height = " + b);
	return true;
    }
    private static void inOrderAssign(RBTnode t, int[] keyCounter) {
	if (t == null) return;
	inOrderAssign(t.lchild, keyCounter);
	t.key = keyCounter[0];
	keyCounter[0] += 1;
	inOrderAssign(t.rchild, keyCounter);
    }

    private static int subtreeSize(RBTnode t) {
	if (t == null) return 0;
	return 1 + subtreeSize(t.lchild) + subtreeSize(t.rchild);
    }
    private static boolean isBST(RBTnode t, long lo, long hi) {
	if (t == null) return true;
	if (t.key < lo || t.key > hi) return false;
	return isBST(t.lchild, lo, (long) t.key - 1)
	    && isBST(t.rchild, (long) t.key + 1, hi);
    }

    public static void main (String[] args) {
	Quiz02_05 outer = new Quiz02_05();

	// Trivial cases.
	System.out.println("isRBT(null)               = " + isRBT(null));
	RBTnode leaf = outer.new RBTnode(); leaf.color = BLACK; leaf.key = 0;
	System.out.println("isRBT(black leaf)         = " + isRBT(leaf));
	RBTnode redRoot = outer.new RBTnode(); redRoot.color = RED;
	System.out.println("isRBT(red root)           = " + isRBT(redRoot));

	// A small valid RBT:
	//        2(B)
	//       /    \
	//      1(R)  3(R)
	RBTnode a = outer.new RBTnode(); a.key = 1; a.color = RED;
	RBTnode c = outer.new RBTnode(); c.key = 3; c.color = RED;
	RBTnode root = outer.new RBTnode(); root.key = 2; root.color = BLACK;
	root.lchild = a; root.rchild = c;
	System.out.println("isRBT(small valid RBT)    = " + isRBT(root));

	// Red node with a red child -> invalid.
	RBTnode rr1 = outer.new RBTnode(); rr1.key = 1; rr1.color = RED;
	RBTnode rr0 = outer.new RBTnode(); rr0.key = 0; rr0.color = RED;
	rr1.lchild = rr0;
	RBTnode rrRoot = outer.new RBTnode(); rrRoot.key = 2; rrRoot.color = BLACK;
	rrRoot.lchild = rr1;
	System.out.println("isRBT(red-red violation)  = " + isRBT(rrRoot));

	// Generate the 1M-key min-black-height RBT.
	long t0 = System.currentTimeMillis();
	boolean ok = genRedBLackBST();
	long t1 = System.currentTimeMillis();
	System.out.println("genRedBLackBST returned " + ok
			   + " in " + (t1 - t0) + " ms");
	System.out.println("verify: isRBT(generated)  = " + isRBT(generatedRoot));
	System.out.println("verify: subtreeSize       = " + subtreeSize(generatedRoot));
	System.out.println("verify: isBST(0..999999)  = "
			   + isBST(generatedRoot, 0, 999999));
	System.out.println("verify: black-height      = " + generatedBlackHeight);
    }
}
