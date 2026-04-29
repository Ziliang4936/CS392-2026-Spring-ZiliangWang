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
    public static boolean isRBT (RBTnode rbt) {
	// HX: Please implement a function that
	// tests whether a given RBTnode is a valid
	// red-black tree. If it is unclear what a
	// red-black tree, you can readily find it on-line
	// Note that you are not asked to check if rbt is
	// a binary search tree in this case.
	return false;
    }
    //
    // HX: 20 points
    // This is largely about understanding red-black trees.
    // Please explain BRIEFLY as to why the generated RBT is
    // of minimal black height (not height).
    //
    public static boolean genRedBLackBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the black height (not height) of this tree is
	// minimal (that is, as small as possible). What is this black
	// height? Please give a brief explanation on your implementation
	// strategy.
    }
    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genRedBlackBST()
	return /*void*/;
    }
}
