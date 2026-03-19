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
	xs = sorted.unlink();
	LnList<T> tail = sorted;
	LnList<T> prevTail = null;

	while (xs.consq1()) {
	    LnList<T> node = xs;
	    xs = node.unlink();

	    if (node.hd1().compareTo(tail.hd1()) >= 0) {
		tail.link(node);
		prevTail = tail;
		tail = node;
	    } else if (prevTail != null && node.hd1().compareTo(prevTail.hd1()) >= 0) {
		prevTail.unlink();
		node.link(tail);
		prevTail.link(node);
		prevTail = node;
	    } else {
		sorted = insertNode(sorted, node);
		if (prevTail == null) {
		    prevTail = sorted;
		}
	    }
	}
	return sorted;
    }

    private static <T extends Comparable<T>>
	LnList<T> insertNode(LnList<T> sorted, LnList<T> node) {
	if (node.hd1().compareTo(sorted.hd1()) < 0) {
	    node.link(sorted);
	    return node;
	}
	LnList<T> prev = sorted;
	LnList<T> curr = sorted.tl1();
	while (curr.consq1() && node.hd1().compareTo(curr.hd1()) >= 0) {
	    prev = curr;
	    curr = curr.tl1();
	}
	prev.unlink();
	node.link(curr);
	prev.link(node);
	return sorted;
    }

    static class ParityInt implements Comparable<ParityInt> {
	int val;
	ParityInt(int v) { val = v; }
	public int compareTo(ParityInt o) { return Integer.compare(val % 2, o.val % 2); }
	public String toString() { return String.valueOf(val); }
    }

    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable

	// --- Test 1: professor's nearly-sorted list (~1M elements) ---
	// Each adjacent pair is swapped: [3,2, 5,4, 7,6, ..., 999999,999998]
	System.out.println("Building nearly-sorted list...");
	LnList<Integer> xs = new LnList<>();
	for (int i = 999999; i >= 2; i -= 2)
	    xs = new LnList<>(i, new LnList<>(i - 1, xs));

	System.out.println("Sorting...");
	long t0 = System.currentTimeMillis();
	LnList<Integer> sorted = LnListInsertSort(xs);
	long t1 = System.currentTimeMillis();
	System.out.println("Done in " + (t1 - t0) + " ms");

	System.out.print("First 5: ");
	LnList<Integer> tmp = sorted;
	for (int i = 0; i < 5; i++) { System.out.print(tmp.hd1() + " "); tmp = tmp.tl1(); }
	System.out.println(); // expect 2 3 4 5 6

	// --- Test 2: parity-sort stability ---
	// Build [0,1,2,...,19]. Sort by parity (even<odd).
	// Stable => evens keep order 0,2,4,...,18 then odds 1,3,5,...,19
	LnList<ParityInt> ps = new LnList<>();
	for (int i = 19; i >= 0; i--) ps = new LnList<>(new ParityInt(i), ps);

	LnList<ParityInt> psorted = LnListInsertSort(ps);
	System.out.print("Parity sort: ");
	psorted.foritm1(x -> System.out.print(x + " "));
	System.out.println(); // expect 0 2 4 6 8 10 12 14 16 18 1 3 5 7 9 11 13 15 17 19
    }
}
