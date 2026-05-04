/*
// HX: 50 points for Final_02
// HX: This one tests your quicksort and mergesort
// In Final_01, pg2701_word$strmize() is implemented
// that lists all the words in pg2701.txt. Here, you
// are asked to generate FnList of pairs; each pair consists
// of a word (FnList<Character>) and a count (Integer) such that
// the count is the number of occurrences of the word in pg2701.txt.
// Note that a lower case letter is considered the same as its
// corresponding upper case. For instance, "Whale" and "whale"
// are considered the same word.
*/

/*
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

import java.util.function.ToIntBiFunction;

public class Final_02 {

    static FnList<FnTupl2<FnList<Character>, Integer>>
	pg2701_word$count$listize2() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Turn this stream into an array A1 of words (FnList<Character>[])
	// 3. Call the quicksort in MyLibrary to sort A1
	// 4. Use sorted A1 to generate a list L2 of word-count pairs
	// 5. Use the mergesort (mergeSort) in MyLibrary to sort L2 using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 6. The sorted L2 is the return value of pg2701_word$count$listize2()
	//
	// HX: (ties to MyLibrary) Step 3 follows FnListSUtil.quickSort’s 3-way
	//    partition/ordering, on A1 as an array so ~222k words do not overflow
	//    the JVM stack (library quickSort is recursive on list size).
	//    Steps 5–6 follow FnListSUtil.mergeSort’s split/merge; merge is
	//    rewritten as a loop because FnListSUtil.merge is Θ(|xs|+|ys|) deep
	//    and overflows when merging long lists of pairs.
	//
	// HX: step 1 — stream of words
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	// HX: step 2 — materialize into array A1
	int[] cnt = new int[]{0};
	final FnList<FnList<Character>>[] tail = new FnList[]{ FnListSUtil.nil() };
	ws.foritm0(w -> {
	    tail[0] = FnListSUtil.cons(w, tail[0]);
	    cnt[0] += 1;
	});
	int N = cnt[0];
	@SuppressWarnings("unchecked")
	FnList<Character>[] A1 = (FnList<Character>[]) new FnList[N];
	{
	    FnList<FnList<Character>> tmp = FnListSUtil.reverse(tail[0]);
	    for (int i = 0; i < N; i += 1) {
		A1[i] = tmp.hd(); tmp = tmp.tl();
	    }
	}
	// HX: step 3 — 3-way quicksort on A1 (uses the same partitioning idea
	//    as FnListSUtil.quickSort, but on an array to satisfy step 2)
	quickSort3$array(A1, word$cmp);
	// HX: step 4 — scan sorted A1 for consecutive runs → word-count pairs (L2)
	FnList<FnTupl2<FnList<Character>, Integer>> pairs$rev = FnListSUtil.nil();
	int i = 0;
	while (i < N) {
	    int j = i + 1;
	    while (j < N && word$cmp.applyAsInt(A1[i], A1[j]) == 0) j += 1;
	    pairs$rev = FnListSUtil.cons(
		new FnTupl2<FnList<Character>, Integer>(A1[i], j - i),
		pairs$rev);
	    i = j;
	}
	FnList<FnTupl2<FnList<Character>, Integer>> pairs =
	    FnListSUtil.reverse(pairs$rev);
	// HX: step 5–6 — mergesort L2 (same divide/conquer as FnListSUtil.mergeSort,
	//    but merge is iterative so merging ~20k pairs does not overflow the stack)
	return mergeSort$stackSafe(pairs, pair$cmp);
    }

    /* HX: MyLibrary's FnListSUtil.merge is recursive with depth Θ(|xs|+|ys|);
       for ~22k distinct words the top-level merge overflows the JVM stack.
       This pair preserves mergeSort's behavior using an iterative merge.
       Package-private so Final_03 can reuse mergeSort without duplicating code. */
    static <T>
	FnList<T> merge$iterative(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T,T> cmp) {
	FnList<T> rev = FnListSUtil.nil();
	while (xs.consq() && ys.consq()) {
	    if (cmp.applyAsInt(xs.hd(), ys.hd()) <= 0) {
		rev = FnListSUtil.cons(xs.hd(), rev);
		xs = xs.tl();
	    } else {
		rev = FnListSUtil.cons(ys.hd(), rev);
		ys = ys.tl();
	    }
	}
	while (xs.consq()) {
	    rev = FnListSUtil.cons(xs.hd(), rev);
	    xs = xs.tl();
	}
	while (ys.consq()) {
	    rev = FnListSUtil.cons(ys.hd(), rev);
	    ys = ys.tl();
	}
	return FnListSUtil.reverse(rev);
    }

    static <T>
	FnList<T> mergeSort$stackSafe(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n = FnListSUtil.length(xs);
	if (n <= 1) return xs;
	int mid = n / 2;
	FnList<T> left = FnListSUtil.nil();
	FnList<T> right = xs;
	for (int j = 0; j < mid; j += 1) {
	    left = FnListSUtil.cons(right.hd(), left);
	    right = right.tl();
	}
	left = FnListSUtil.reverse(left);
	left = mergeSort$stackSafe(left, cmp);
	right = mergeSort$stackSafe(right, cmp);
	return merge$iterative(left, right, cmp);
    }

    // HX: lex comparator on FnList<Character>
    static final ToIntBiFunction<FnList<Character>, FnList<Character>>
	word$cmp = (xs, ys) -> {
	    while (xs.consq() && ys.consq()) {
		int d = (int) xs.hd() - (int) ys.hd();
		if (d != 0) return d;
		xs = xs.tl(); ys = ys.tl();
	    }
	    if (xs.nilq() && ys.nilq()) return 0;
	    return xs.nilq() ? -1 : 1;
	};

    // HX: pair comparator: count DESC, then word ASC
    static final
	ToIntBiFunction<FnTupl2<FnList<Character>, Integer>,
			FnTupl2<FnList<Character>, Integer>>
	pair$cmp = (a, b) -> {
	    int dn = b.sub1 - a.sub1;
	    if (dn != 0) return dn;
	    return word$cmp.applyAsInt(a.sub0, b.sub0);
	};

    // HX: iterative 3-way array quicksort using an explicit stack of
    // (lo, hi) ranges. Uses median-of-three pivot to avoid worst-case
    // behavior on already-ordered or skewed inputs.
    private static <T> void
	quickSort3$array(T[] A, ToIntBiFunction<T,T> cmp) {
	int n = A.length;
	if (n <= 1) return;
	int[] lostk = new int[64];
	int[] histk = new int[64];
	int top = 0;
	lostk[top] = 0; histk[top] = n - 1; top += 1;
	while (top > 0) {
	    top -= 1;
	    int lo = lostk[top]; int hi = histk[top];
	    while (lo < hi) {
		// median-of-three to pick the pivot
		int mid = lo + (hi - lo) / 2;
		T a = A[lo]; T b = A[mid]; T c = A[hi];
		T pivot;
		if (cmp.applyAsInt(a, b) <= 0) {
		    if (cmp.applyAsInt(b, c) <= 0) pivot = b;
		    else if (cmp.applyAsInt(a, c) <= 0) pivot = c;
		    else pivot = a;
		} else {
		    if (cmp.applyAsInt(a, c) <= 0) pivot = a;
		    else if (cmp.applyAsInt(b, c) <= 0) pivot = c;
		    else pivot = b;
		}
		// 3-way partition (Dijkstra)
		int lt = lo; int gt = hi; int k = lo;
		while (k <= gt) {
		    int s = cmp.applyAsInt(A[k], pivot);
		    if (s < 0) { T tmp = A[lt]; A[lt] = A[k]; A[k] = tmp; lt += 1; k += 1; }
		    else if (s > 0) { T tmp = A[k]; A[k] = A[gt]; A[gt] = tmp; gt -= 1; }
		    else k += 1;
		}
		// push the larger range, loop on the smaller range
		int leftSize = lt - lo;
		int rightSize = hi - gt;
		if (top + 1 >= lostk.length) {
		    int newCap = lostk.length * 2;
		    int[] nl = new int[newCap]; int[] nh = new int[newCap];
		    for (int t = 0; t < top; t += 1) { nl[t] = lostk[t]; nh[t] = histk[t]; }
		    lostk = nl; histk = nh;
		}
		if (leftSize > rightSize) {
		    if (leftSize > 1) { lostk[top] = lo; histk[top] = lt - 1; top += 1; }
		    if (rightSize > 1) { lo = gt + 1; }
		    else break;
		} else {
		    if (rightSize > 1) { lostk[top] = gt + 1; histk[top] = hi; top += 1; }
		    if (leftSize > 1) { hi = lt - 1; }
		    else break;
		}
	    }
	}
    }

    // HX: helper to print a word as plain text
    private static void print_word(FnList<Character> w) {
	FnList<Character> cur = w;
	while (cur.consq()) {
	    System.out.print(cur.hd()); cur = cur.tl();
	}
    }

    public static void main(String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize2()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	FnList<FnTupl2<FnList<Character>, Integer>> res =
	    pg2701_word$count$listize2();
	int i = 0;
	FnList<FnTupl2<FnList<Character>, Integer>> cur = res;
	while (i < 100 && cur.consq()) {
	    FnTupl2<FnList<Character>, Integer> p = cur.hd();
	    System.out.print((i+1) + ": ");
	    print_word(p.sub0);
	    System.out.println(" " + p.sub1);
	    cur = cur.tl(); i += 1;
	}
	return;
    }
}
