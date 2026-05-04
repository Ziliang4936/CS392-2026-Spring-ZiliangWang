/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/

/*
import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;
*/

public class Final_01 {
    static LnStrm<FnList<Character>> pg2701_word$strmize() {
	// HX-2026-05-04:
	// Please construct a stream of words contained in the
	// file Data/pg2701.txt
	// Note that a word is represented as a list of characters
	// in the English alphabet plus aprostrophe (')
	// Also every upper case letter in the original text should
	// be turned into its corresponding lower case.
	// This stream should be built on top of pg2701_char$strmize
	// which is already implemented in Final_00.
	// In particular, you should NOT use Java library functions
	// for processing files!
    }
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$strmize()
	return /*void*/;
    }
}
