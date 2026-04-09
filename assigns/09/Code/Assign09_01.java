//
// HX-2026-04-09: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
public class Quiz09_01 {
    Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }
    public boolean insert(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [insert] does nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
    }
    public boolean remove(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [remove] removes the key and the new tree
	// obtained is still a binary search tree
	// If key is not in the tree stored at [root],
	// [remove] does nothing and returns false
	// to indicate no removal of the key k done.
    }
    public static void main (String[] args) {
	// Please add minimal testing code for insert()
	// Please add minimal testing code for remove()
	return /*void*/;
    }
}
