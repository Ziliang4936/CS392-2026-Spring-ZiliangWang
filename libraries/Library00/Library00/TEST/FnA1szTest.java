import Library00.FnList.*;
import Library00.FnA1sz.*;

public class FnA1szTest {
//
    public static Integer tally(int n) {
	return FnA1szSUtil.folditm
	    (FnA1szSUtil.int1$make(n), 0, (r, i) -> r + (i+1));
    }
    public static Integer factorial(int n) {
	return FnA1szSUtil.folditm
	    (FnA1szSUtil.int1$make(n), 1, (r, i) -> r * (i+1));
    }
//
    public static void main(String[] args) {
	FnA1sz<Integer> xs =
	    FnA1szSUtil.int1$make(10);
	xs.System$out$print(); System.out.println();
	xs = FnA1szSUtil.map_array(xs, (x0) -> x0 * x0);
	xs.System$out$print(); System.out.println();
	xs = FnA1szSUtil.imap_array(xs, (i0, x0) -> 10 * (100-x0) + i0);
	xs.System$out$print(); System.out.println();
	System.out.println("tally(10) = " + tally(10).toString());
	System.out.println("factorial(10) = " + factorial(10).toString());
    }
//
} // end of [public class FnA1szTest{...}]
