import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class BinarySearch {
    public BinarySearch() { }
    
    public int indexOf(int[] A, int key) {
        int lo = 0;
        int hi = A.length - 1;
	// HX: Do search in A[lo..hi]
	while (lo <= hi) {
            // Key is in A[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
            if      (key < A[mid]) hi = mid - 1;
            else if (key > A[mid]) lo = mid + 1;
            else return mid;
        }
	return -1;
    }

    public static void main(String[] args) {
	
	// read the integers from a file
	In in = new In(args[0]);
	int[] allowlist = in.readAllInts();

	// sort the array
	Arrays.sort(allowlist);

	BinarySearch BS = new BinarySearch();

	// read integer key from standard input; print if not in Allowlist
	while (!StdIn.isEmpty()) {
	    Integer key = StdIn.readInt();
	    if (BS.indexOf(allowlist, key) != -1)
		{
		    StdOut.println("key(" + key + ") found!");
		}
	    else
		{
		    StdOut.println("key(" + key + ") not found!");
		}
	}
    }
}
