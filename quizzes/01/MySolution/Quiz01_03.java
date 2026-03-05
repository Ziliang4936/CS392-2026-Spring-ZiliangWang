//
// HX-2026-03-04: 30 points
// This one may seem easy but can be time-consuming
// if you use a brute-force approach.
// Hint: Try to think about implementing bubble-sort
// without recursion
//
public class Quiz01_03 {
    @SuppressWarnings("unchecked")
    public static
	<T extends Comparable<T>>
	T[] sort20WithNoRecursion
	(T x00, T x01, T x02, T x03, T x04, T x05, T x06, T x07, T x08, T x09,
	 T x10, T x11, T x12, T x13, T x14, T x15, T x16, T x17, T x18, T x19) {
	// HX-2026-03-03:
	// Given 30 arguments,
	// please return an array of size 20 containing the
	// 20 arguments sorted according to the order implemented by
	// compareTo on T.
	// HX: No recursion is allowed for this one
	// HX: No loops (either while-loop or for-loop) is allowed.
	// HX: Yes, you can use functions (but not recursive functions)
	// HX: Please do not try to write a HUGE if-then-else mumble jumble!
	T[] a = (T[]) new Comparable[20];
	a[ 0]=x00; a[ 1]=x01; a[ 2]=x02; a[ 3]=x03; a[ 4]=x04;
	a[ 5]=x05; a[ 6]=x06; a[ 7]=x07; a[ 8]=x08; a[ 9]=x09;
	a[10]=x10; a[11]=x11; a[12]=x12; a[13]=x13; a[14]=x14;
	a[15]=x15; a[16]=x16; a[17]=x17; a[18]=x18; a[19]=x19;
	// 19 explicit calls to a non-recursive bubblePass.
	// After k passes the k largest elements are in their correct positions,
	// so 19 passes guarantee the full array is sorted.
	bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a);
	bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a);
	bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a);
	bubblePass(a); bubblePass(a); bubblePass(a); bubblePass(a);
	return a;
    }

    // One full bubble pass over all 19 adjacent pairs — no loops, not recursive.
    private static <T extends Comparable<T>> void bubblePass(T[] a) {
	swap(a, 0, 1);  swap(a, 1, 2);  swap(a, 2, 3);  swap(a, 3, 4);
	swap(a, 4, 5);  swap(a, 5, 6);  swap(a, 6, 7);  swap(a, 7, 8);
	swap(a, 8, 9);  swap(a, 9,10);  swap(a,10,11);  swap(a,11,12);
	swap(a,12,13);  swap(a,13,14);  swap(a,14,15);  swap(a,15,16);
	swap(a,16,17);  swap(a,17,18);  swap(a,18,19);
    }

    // Swap a[i] and a[j] if a[i] > a[j] — not recursive.
    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
	if (a[i].compareTo(a[j]) > 0) {
	    T tmp = a[i]; a[i] = a[j]; a[j] = tmp;
	}
    }

    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for sort20WithNoRecursion.

	// Integer: reverse-sorted input
	Object[] res = sort20WithNoRecursion(
	    20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1);
	System.out.print("Integer: ");
	for (int i = 0; i < 20; i++) System.out.print(res[i] + " ");
	System.out.println(); // expect 1 2 3 ... 20

	// String: unsorted strings
	Object[] sres = sort20WithNoRecursion(
	    "t","s","r","q","p","o","n","m","l","k",
	    "j","i","h","g","f","e","d","c","b","a");
	System.out.print("String:  ");
	for (int i = 0; i < 20; i++) System.out.print(sres[i] + " ");
	System.out.println(); // expect a b c ... t
    }
}
