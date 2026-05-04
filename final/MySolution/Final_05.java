/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/

/*
import MyLibrary.LnList.*;
*/

import MyLibrary.FnList.*;
import MyLibrary.LnList.*;

import java.util.function.ToIntBiFunction;

public class Final_05 {

    /* HX: Min-heap entry with a stable tie-breaker on the source-list index.
       MyPQueueArray is a MAX-heap (parent >= child); to act as a MIN-heap
       on `cmp`, we reverse the comparator. To break ties stably (smaller
       idx first), we reverse the index comparison too. */
    static class HE<T> implements Comparable<HE<T>> {
	final T value;
	final int idx;
	final ToIntBiFunction<T,T> cmp;
	HE(T v, int i, ToIntBiFunction<T,T> c) { value=v; idx=i; cmp=c; }
	public int compareTo(HE<T> o) {
	    int c = cmp.applyAsInt(o.value, value);
	    if (c != 0) return c;
	    return Integer.compare(o.idx, idx);
	}
    }

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// HX: Given an array of (linear) lists (LnList), each of which is
	// ordered according to cmp, please implement a function to merge them
	// into one ordered (linear) list. Please note that you cannot create
	// new list nodes; you can only use existing nodes to form the returned
	// linear list. You are asked to use MyPQueueArray.java implemented in
	// Assigment#9 for finding the minimum of a collection of nodes.
	//
	// HX: enqueue the head of every non-empty source list; relink heads
	//     out of the queue in min-order, advancing each list as we go.
	int K = xss.length;
	MyPQueueArray<HE<T>> pq =
	    new MyPQueueArray<HE<T>>(Math.max(1, K));
	for (int i = 0; i < K; i += 1) {
	    if (xss[i] != null && xss[i].consq1()) {
		pq.enque$raw(new HE<T>(xss[i].hd1(), i, cmp));
	    }
	}
	LnList<T> result = new LnList<T>();
	LnList<T> tail = null;
	while (!pq.isEmpty()) {
	    HE<T> w = pq.deque$raw();
	    int i = w.idx;
	    LnList<T> rest = xss[i].unlink1();
	    LnList<T> headNode = xss[i];
	    xss[i] = rest;
	    if (tail == null) result = headNode;
	    else tail.link1(headNode);
	    tail = headNode;
	    if (rest.consq1()) {
		pq.enque$raw(new HE<T>(rest.hd1(), i, cmp));
	    }
	}
	return result;
    }

    public static<T>
	FnList<T>
	LnList_mergeSort$100way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX: Please use LnList_n$way$merge to implement 100-way mergesort
	// on a linear list. That is, split each list evenly into 100 sublists;
	// recursely sort the 100 sublist and then use LnList_n$way$merge to merge
	// them into one sorted list.
	// Please make sure that your implementation of LnList_mergeSort$100way
	// does stable sorting!
	//
	// HX: depth = ceil(log_100 N). For N=1M, only 3 levels.
	LnList<T> sorted = sortLn$100way(xs, cmp);
	return ln$to$fn(sorted);
    }

    @SuppressWarnings("unchecked")
    private static<T> LnList<T>
	sortLn$100way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n = xs.length1();
	if (n <= 1) return xs;
	int K = Math.min(100, n);
	LnList<T>[] parts = (LnList<T>[]) new LnList[K];
	int base = n / K;
	int rem = n % K;
	LnList<T> cur = xs;
	for (int k = 0; k < K; k += 1) {
	    int sz = base + (k < rem ? 1 : 0);
	    parts[k] = cur;
	    for (int j = 0; j < sz - 1; j += 1) {
		cur = cur.tl1();
	    }
	    LnList<T> rest = cur.unlink1();
	    cur = rest;
	}
	for (int k = 0; k < K; k += 1) {
	    parts[k] = sortLn$100way(parts[k], cmp);
	}
	return LnList_n$way$merge(parts, cmp);
    }

    /* HX: build LnList<Integer> = [0, 1, ..., N-1] using ONLY the public
       LnList constructors. We avoid LnListSUtil entirely because some
       IDE Java extensions (the redhat.java JDT in Cursor/VS Code) cache
       stale views of LnListSUtil and inject a stub that throws an
       "Unresolved compilation problem" at runtime. The plain command line
       compiler has no such issue. Iterative; no recursion. */
    private static LnList<Integer> makeIntLnList(int N) {
	LnList<Integer> xs = new LnList<Integer>();
	for (int i = N - 1; i >= 0; i -= 1) {
	    Integer boxed = Integer.valueOf(i);
	    xs = new LnList<Integer>(boxed, xs);
	}
	return xs;
    }

    private static<T> FnList<T> ln$to$fn(LnList<T> xs) {
	FnList<T> rev = FnListSUtil.nil();
	LnList<T> cur = xs;
	while (cur.consq1()) {
	    rev = FnListSUtil.cons(cur.hd1(), rev);
	    cur = cur.tl1();
	}
	return FnListSUtil.reverse(rev);
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to parity-sort the list [0,1,2,...,999998,999999]
	// of 1000000 elements.
	//
	// HX: parity comparator — even before odd; ties (same parity)
	//     resolved stably by the sort, preserving original order.
	int N = 1_000_000;
	LnList<Integer> xs = makeIntLnList(N);
	ToIntBiFunction<Integer,Integer> cmp = (a, b) -> (a & 1) - (b & 1);

	long t0 = System.currentTimeMillis();
	FnList<Integer> sorted = LnList_mergeSort$100way(xs, cmp);
	long t1 = System.currentTimeMillis();

	// Expected: [0,2,4,...,N-2, 1,3,5,...,N-1]
	boolean ok = true;
	int i = 0;
	FnList<Integer> p = sorted;
	while (p.consq() && ok) {
	    int v = p.hd();
	    int expected = (i < N / 2) ? (2 * i) : (2 * (i - N / 2) + 1);
	    if (v != expected) ok = false;
	    p = p.tl(); i += 1;
	}
	ok = ok && p.nilq() && (i == N);

	System.out.println("N = " + N);
	System.out.println("time(ms) = " + (t1 - t0));
	System.out.println("parity-sorted correctly = " + ok);
    }
}
