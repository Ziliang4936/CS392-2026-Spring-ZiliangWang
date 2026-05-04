import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnGtree.*;

public class QueenPuzzleTest {
//
    static int nsol = 0;
    static final int N = QueenBoard.N;
//
    public static void
	row$print(int c0) {
	for (int c1 = 0; c1 < N; c1 += 1)
	{
	    System.out.print(c0 != c1 ? ". " : "Q ");
	}
	System.out.println();
    }
    public static void
	board$print(FnList<Integer> cs) {
	FnListSUtil.rforitm(cs, (col) -> row$print(N-1-col));
	System.out.println();
    }
//
    public static void main(String[] args) {
	QueenBoard bd0 =
	    new QueenBoard(new FnList<Integer>());
	LnStrm<FnList<Integer>> sols =
	    (FnGtreeSUtil.DFirstEnumerate(bd0)).filter0((cs) -> cs.length() == N);
	sols.foritm0(
	     (cs) -> {
		 nsol += 1;
		 System.out.println("Solution(" + nsol + "):");
		 board$print(cs);
	     }

	);
    }
//
} // end of [public class QueenPuzzleTest{...}]
