/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/

/*
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyMap.*;

public class Final_03 {

    static FnList<FnTupl2<FnList<Character>, Integer>>
	pg2701_word$count$listize3() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the hash map implemented in Assign08_02 (open addressing)
	//    to count the number of occurrences of each word in the stream of words
	// 3. Then figure out a way to turn the hash map into a list WNS (FnList) of
	//    word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize3()
	//
	// HX: Step 2 uses MyLibrary.MyMap.MyMapOAddr<Integer> (open addressing).
	//    Keys are Java String (same spelling as FnList<Character> words).
	// HX: Step 4 — same merge order as FnListSUtil.mergeSort in Assign05_01;
	//    Final_02.mergeSort$stackSafe uses iterative merge (stack-safe).
	//
	// HX: step 1
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	// HX: step 2 — count via open-addressing hash map
	//     capacity >> distinct words (~22k) to avoid MyMapFullExn
	MyMapOAddr<Integer> map = new MyMapOAddr<Integer>(262144);
	ws.foritm0(w -> {
	    String k = fnList$chars$stringize(w);
	    Integer old = map.search$opt(k);
	    if (old == null) {
		map.insert$opt(k, 1);
	    } else {
		map.insert$opt(k, old + 1);
	    }
	});
	// HX: step 3 — map → FnList of (word, count); order arbitrary before sort
	final FnList<FnTupl2<FnList<Character>, Integer>>[] box =
	    new FnList[]{ FnListSUtil.nil() };
	map.foritm((key, n) -> {
	    FnList<Character> w = string$fnList(key);
	    box[0] = FnListSUtil.cons(
		new FnTupl2<FnList<Character>, Integer>(w, n),
		box[0]);
	});
	FnList<FnTupl2<FnList<Character>, Integer>> wns =
	    FnListSUtil.reverse(box[0]);
	// HX: steps 4–5
	return Final_02.mergeSort$stackSafe(wns, Final_02.pair$cmp);
    }

    // HX: FnList<Character> → String (for MyMapOAddr<String,?> keys)
    static String fnList$chars$stringize(FnList<Character> w) {
	StringBuilder sb = new StringBuilder(FnListSUtil.length(w));
	FnList<Character> cur = w;
	while (cur.consq()) {
	    sb.append(cur.hd());
	    cur = cur.tl();
	}
	return sb.toString();
    }

    // HX: String → FnList<Character> (inverse of fnList$chars$stringize)
    static FnList<Character> string$fnList(String s) {
	FnList<Character> rev = FnListSUtil.nil();
	for (int i = s.length() - 1; i >= 0; i -= 1) {
	    rev = FnListSUtil.cons(s.charAt(i), rev);
	}
	return rev;
    }

    private static void print_word(FnList<Character> w) {
	FnList<Character> cur = w;
	while (cur.consq()) {
	    System.out.print(cur.hd()); cur = cur.tl();
	}
    }

    public static void main(String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize3()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	FnList<FnTupl2<FnList<Character>, Integer>> res =
	    pg2701_word$count$listize3();
	int i = 0;
	FnList<FnTupl2<FnList<Character>, Integer>> cur = res;
	while (i < 100 && cur.consq()) {
	    FnTupl2<FnList<Character>, Integer> p = cur.hd();
	    System.out.print((i + 1) + ": ");
	    print_word(p.sub0);
	    System.out.println(" " + p.sub1);
	    cur = cur.tl(); i += 1;
	}
	return;
    }
}
