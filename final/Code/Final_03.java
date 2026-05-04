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

public class Final_03 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
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
    }
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize3()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	return /*void*/;
    }
}
