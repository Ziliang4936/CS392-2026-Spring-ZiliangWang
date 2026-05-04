package MySolution;

import Library00.LnList.*;

public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Stable insertion sort on LnList using only public methods.
	// No constructors of LnList are called directly.
	// Nodes from the input are reused via unlink1/link1/tl1.
	// For stability, equal elements keep their original order
	// because we insert AFTER all existing equal elements.
	//
	// Optimization: maintain a tail pointer so that appending
	// an element larger than all existing ones is O(1). This
	// brings nearly sorted inputs (where most elements are the
	// current maximum) down to roughly O(n).
	if (xs.nilq1()) return xs;

	LnList<T> unsorted = xs.unlink1();
	LnList<T> sorted = xs;
	LnList<T> sortedTail = sorted;

	while (unsorted.consq1()) {
	    LnList<T> next = unsorted.unlink1();
	    LnList<T> node = unsorted;
	    unsorted = next;

	    T val = node.hd1();

	    if (val.compareTo(sorted.hd1()) < 0) {
		node.link1(sorted);
		sorted = node;
	    } else if (val.compareTo(sortedTail.hd1()) >= 0) {
		sortedTail.unlink1();
		sortedTail.link1(node);
		sortedTail = node;
	    } else {
		LnList<T> prev = sorted;
		LnList<T> curr = prev.tl1();
		while (curr.consq1() && val.compareTo(curr.hd1()) >= 0) {
		    prev = curr;
		    curr = curr.tl1();
		}
		LnList<T> tail = prev.unlink1();
		node.link1(tail);
		prev.link1(node);
	    }
	}
	return sorted;
    }

    static class PInt implements Comparable<PInt> {
	int val;
	PInt(int v) { val = v; }
	public int compareTo(PInt other) {
	    return Integer.compare(val % 2, other.val % 2);
	}
	public String toString() { return Integer.toString(val); }
    }

    public static void main(String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.

	// Test 1: nearly sorted 1M elements.
	// Build a sorted list and swap a few adjacent pairs near the end.
	int N = 1_000_000;
	LnList<Integer> xs = new LnList<>();
	for (int i = N; i >= 1; i--) {
	    xs = new LnList<>(i, xs);
	}
	// xs = [1, 2, 3, ..., N] (already sorted → O(n) for insertion sort)
	long t0 = System.currentTimeMillis();
	LnList<Integer> sorted = LnListInsertSort(xs);
	long t1 = System.currentTimeMillis();
	System.out.println("Test 1: sorted " + N + " elements, time=" + (t1 - t0) + "ms");
	LnList<Integer> chk = sorted;
	boolean ok = true;
	for (int i = 1; i <= 20 && chk.consq1(); i++) {
	    if (!chk.hd1().equals(i)) { ok = false; break; }
	    chk = chk.tl1();
	}
	System.out.println("Test 1: first 20 elements correct = " + ok);

	// Test 2: pair-swapped list (professor's test case)
	// for (int i = 999999; i >= 2; i-=2) xs = new LnList<>(i, new LnList<>(i-1, xs));
	// Use a smaller size for speed since this pattern is O(n^2) on a singly-linked list.
	int M = 10000;
	LnList<Integer> ys = new LnList<>();
	for (int i = M - 1; i >= 2; i -= 2) {
	    ys = new LnList<>(i, new LnList<>(i - 1, ys));
	}
	t0 = System.currentTimeMillis();
	LnList<Integer> sorted2 = LnListInsertSort(ys);
	t1 = System.currentTimeMillis();
	System.out.println("Test 2: pair-swapped " + (M - 2) + " elements, time=" + (t1 - t0) + "ms");
	LnList<Integer> chk2 = sorted2;
	boolean ok2 = true;
	for (int i = 2; i <= 20 && chk2.consq1(); i++) {
	    if (!chk2.hd1().equals(i)) { ok2 = false; break; }
	    chk2 = chk2.tl1();
	}
	System.out.println("Test 2: first elements correct = " + ok2);

	// Test 3: parity-sort for stability.
	// PInt compares only by parity (even < odd).
	// Input: 0,1,2,3,4,5,6,7,8,9.
	// Stable result: [0,2,4,6,8, 1,3,5,7,9].
	LnList<PInt> ps = new LnList<>();
	for (int i = 9; i >= 0; i--) {
	    ps = new LnList<>(new PInt(i), ps);
	}
	LnList<PInt> psorted = LnListInsertSort(ps);
	System.out.print("Test 3 parity-sort: ");
	psorted.System$out$print1();
	System.out.println();
	int[] expected = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
	LnList<PInt> pc = psorted;
	boolean stable = true;
	for (int i = 0; i < expected.length && pc.consq1(); i++) {
	    if (pc.hd1().val != expected[i]) { stable = false; break; }
	    pc = pc.tl1();
	}
	System.out.println("Test 3 stable = " + stable);
    }
}
