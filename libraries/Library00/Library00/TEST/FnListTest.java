import Library00.FnList.*;
    
public class FnListTest {
//
    static int abs(int x) {
	return (x >= 0 ? x : -x);
    }
//
    public static Integer tally(int n) {
	return FnListSUtil.folditm
	    (FnListSUtil.int1$make(n), 0, (r, i) -> r + (i+1));
    }
    public static Integer factorial(int n) {
	return FnListSUtil.folditm
	    (FnListSUtil.int1$make(n), 1, (r, i) -> r * (i+1));
    }
//
    public static void main(String[] args) {
	FnList<Integer> xs =
	    FnListSUtil.int1$make(10);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.map_list(xs, (x0) -> 10-x0);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.map_list(xs, (x0) -> x0 * x0);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.imap_list(xs, (i0, x0) -> 10 * x0 + i0);
	xs.System$out$print(); System.out.println();
//
	System.out.println("tally(10) = " + tally(10).toString());
	System.out.println("factorial(10) = " + factorial(10).toString());
//
    }
//
} // end of [public class FnListTest{...}]
