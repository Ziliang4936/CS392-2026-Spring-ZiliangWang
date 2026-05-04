/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/

import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {

    static LnStrm<FnList<Character>> pg2701_word$strmize() {
	// HX-2026-05-04:
	// Build a stream of words (FnList<Character>) lazily on top of
	// pg2701_char$strmize() from Final_00.
	// All upper-case letters are lower-cased via ASCII arithmetic.
	return word$strmize(Final_00.pg2701_char$strmize());
    }

    // HX: produce a lazy stream of words from a lazy stream of characters
    private static LnStrm<FnList<Character>>
	word$strmize(final LnStrm<Character> cstrm) {
	return new LnStrm<FnList<Character>>(() -> {
	    LnStcn<Character> cxs = cstrm.eval0();
	    while (cxs.consq() && !is_word$char(cxs.hd())) {
		cxs = cxs.tl().eval0();
	    }
	    if (cxs.nilq()) {
		return new LnStcn<FnList<Character>>();
	    }
	    FnList<Character> word$rev = FnListSUtil.nil();
	    while (cxs.consq() && is_word$char(cxs.hd())) {
		char c = to_lower$char(cxs.hd());
		word$rev = FnListSUtil.cons(c, word$rev);
		cxs = cxs.tl().eval0();
	    }
	    FnList<Character> word = FnListSUtil.reverse(word$rev);
	    final LnStrm<Character> rest;
	    if (cxs.nilq()) {
		rest = new LnStrm<Character>();
	    } else {
		// HX: rebuild a stream whose eval0 yields the
		// FIRST non-word LnStcn we already consumed
		final LnStcn<Character> head = cxs;
		rest = new LnStrm<Character>(() -> head);
	    }
	    return new LnStcn<FnList<Character>>(word, word$strmize(rest));
	});
    }

    private static boolean is_word$char(char c) {
	return ('a' <= c && c <= 'z')
	    || ('A' <= c && c <= 'Z')
	    || c == '\'';
    }
    private static char to_lower$char(char c) {
	return ('A' <= c && c <= 'Z') ? (char)(c + ('a' - 'A')) : c;
    }

    // HX: helper to print a FnList<Character> as a plain word
    private static void print_word(FnList<Character> w) {
	FnList<Character> cur = w;
	while (cur.consq()) {
	    System.out.print(cur.hd()); cur = cur.tl();
	}
    }

    public static void main(String[] args) {
	// HX-2025-12-16: minimal testing -- print first 30 words
	LnStrm<FnList<Character>> ws = pg2701_word$strmize();
	int i = 0;
	while (i < 30) {
	    LnStcn<FnList<Character>> cxs = ws.eval0();
	    if (cxs.nilq()) break;
	    System.out.print((i+1) + ": ");
	    print_word(cxs.hd());
	    System.out.println();
	    ws = cxs.tl();
	    i += 1;
	}
	return;
    }
}
