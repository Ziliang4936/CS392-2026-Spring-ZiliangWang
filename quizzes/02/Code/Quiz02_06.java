//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
public class Quiz02_06 {
    Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }
    public void reroot() {
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
    }
    public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does no nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
    }
    public static void main (String[] args) {
	// Please add minimal testing code for reroot()
	// Please add minimal testing code for insert()
	return /*void*/;
    }
}
