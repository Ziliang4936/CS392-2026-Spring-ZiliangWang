//
// HX: 40 points
//
public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement (stable) insertion sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class; you cannot use any constructors
	// in LnList
	//
	// Strategy: take each node from xs via unlink(), insert it into
	// a growing sorted list using link/unlink only (no constructors).
	// A tail pointer enables O(1) append when the new element >=
	// the current maximum, making the sort O(n) on nearly-sorted input.
	if (xs.nilq1()) return xs;

	LnList<T> sorted = xs;
	xs = sorted.unlink();       // sorted = [first node], xs = remainder
	LnList<T> tail = sorted;    // tail always points to the last node

	while (xs.consq1()) {
	    LnList<T> node = xs;
	    xs = node.unlink();     // detach one node; node.root.tail is now null

	    if (node.hd1().compareTo(tail.hd1()) >= 0) {
		// Fast path: append at end (covers equal elements for stability)
		tail.link(node);
		tail = node;
	    } else {
		// Insert node into the correct position inside sorted
		sorted = insertNode(sorted, node);
		// tail is unchanged: node was inserted before the current tail
	    }
	}
	return sorted;
    }

    // Insert a single-element list 'node' into the sorted list.
    // Stable: inserts after all existing elements with equal key.
    // No LnList constructors used.
    private static <T extends Comparable<T>>
	LnList<T> insertNode(LnList<T> sorted, LnList<T> node) {
	if (node.hd1().compareTo(sorted.hd1()) < 0) {
	    // node is smaller than the head: prepend
	    node.link(sorted);
	    return node;
	}
	// Traverse to find the rightmost position where sorted element <= node
	// (>= keeps equal elements in original order -> stable)
	LnList<T> prev = sorted;
	LnList<T> curr = sorted.tl1();
	while (curr.consq1() && node.hd1().compareTo(curr.hd1()) >= 0) {
	    prev = curr;
	    curr = curr.tl1();
	}
	// Splice: prev -> node -> curr
	prev.unlink();   // sever prev from curr (prev.root.tail = null)
	node.link(curr); // node -> curr
	prev.link(node); // prev -> node
	return sorted;
    }

    // ---------------------------------------------------------------
    // TaggedInt: an Integer with an original-position tag for
    // testing sort stability (compare by val only).
    // ---------------------------------------------------------------
    static class TaggedInt implements Comparable<TaggedInt> {
	int val, tag;
	TaggedInt(int v, int t) { val = v; tag = t; }
	public int compareTo(TaggedInt o) { return Integer.compare(val, o.val); }
	public String toString() { return val + "^" + tag; }
    }

    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable

	// --- Test 1: nearly sorted 1M elements ---
	// Build [1, 0, 2, 3, 4, ..., 999999]: only first two elements swapped.
	// With the tail-append optimisation, insertions after the first are O(1),
	// giving O(n) total on this nearly-sorted input.
	System.out.println("Building 1M nearly-sorted list...");
	LnList<Integer> xs = new LnList<>();
	for (int i = 999999; i >= 2; i--) xs = new LnList<>(i, xs);
	xs = new LnList<>(0, xs);
	xs = new LnList<>(1, xs);
	// xs = [1, 0, 2, 3, ..., 999999]

	System.out.println("Sorting 1M elements...");
	long t0 = System.currentTimeMillis();
	LnList<Integer> sorted = LnListInsertSort(xs);
	long t1 = System.currentTimeMillis();
	System.out.println("Done in " + (t1 - t0) + " ms");

	// Spot-check first five elements
	System.out.print("First 5: ");
	LnList<Integer> tmp = sorted;
	for (int i = 0; i < 5; i++) {
	    System.out.print(tmp.hd1() + " ");
	    tmp = tmp.tl1();
	}
	System.out.println(); // expect 0 1 2 3 4

	// --- Test 2: stability via TaggedInt ---
	// Build [1^1, 2^1, 3^1, 2^2, 3^2, 1^2]
	LnList<TaggedInt> ts = new LnList<>();
	ts = new LnList<>(new TaggedInt(1, 2), ts);
	ts = new LnList<>(new TaggedInt(3, 2), ts);
	ts = new LnList<>(new TaggedInt(2, 2), ts);
	ts = new LnList<>(new TaggedInt(3, 1), ts);
	ts = new LnList<>(new TaggedInt(2, 1), ts);
	ts = new LnList<>(new TaggedInt(1, 1), ts);
	// ts = [1^1, 2^1, 3^1, 2^2, 3^2, 1^2]

	LnList<TaggedInt> stableSorted = LnListInsertSort(ts);
	System.out.print("Stable sort: ");
	stableSorted.foritm1(x -> System.out.print(x + " "));
	System.out.println(); // expect 1^1 1^2 2^1 2^2 3^1 3^2
    }
}
