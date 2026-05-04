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

public class Final_02 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Turn this stream into an array A1 of words (FnList<Character>[])
	// 3. Call the quicksort in MyLibrary to sort A1
	// 4. Use sorted A1 to generate a list L2 of word-count pairs
	// 5. Use the mergesort (mergeSort) in MyLibrary to sort L2 using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 6. The sorted L2 is the return value of pg2701_word$count$listize2()
    }
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize2()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	return /*void*/;
    }
}
