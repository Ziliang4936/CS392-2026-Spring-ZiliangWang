/*
HX-2026-02-13: 20 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_02 {
    static FnStrn
	FnList$FnStrn_concate(FnList<FnStrn> xs) {
	int[] totalLen = {0};
	FnListSUtil.foritm(xs, (s) -> {
	    totalLen[0] += s.length();
	});

	char[] res = new char[totalLen[0]];
	int[] pos = {0};
	FnListSUtil.foritm(xs, (s) -> {
	    FnStrnSUtil.foritm(s, (c) -> {
		res[pos[0]] = c;
		pos[0] += 1;
	    });
	});

	return new FnStrn(res);
    }

    public static void main(String[] argv) {
	FnList<FnStrn> empty = new FnList<>();

	FnStrn a = new FnStrn("a");
	FnStrn bc = new FnStrn("bc");
	FnStrn def = new FnStrn("def");

	FnList<FnStrn> list1 =
	    new FnList<>(a,
	    new FnList<>(bc,
	    new FnList<>(def, empty)));

	FnStrn r1 = FnList$FnStrn_concate(list1);
	System.out.println("concate(a, bc, def) = ");
	FnStrnSUtil.foritm(r1, (c) -> System.out.print(c));
	System.out.println();

	FnStrn r2 = FnList$FnStrn_concate(empty);
	System.out.println("concate() = ");
	FnStrnSUtil.foritm(r2, (c) -> System.out.print(c));
	System.out.println();

	FnList<FnStrn> list2 =
	    new FnList<>(new FnStrn("hello"), 
	    new FnList<>(new FnStrn(" "),
	    new FnList<>(new FnStrn("world"), empty)));

	FnStrn r3 = FnList$FnStrn_concate(list2);
	System.out.println("concate(hello, ' ', world) = ");
	FnStrnSUtil.foritm(r3, (c) -> System.out.print(c));
	System.out.println();
    }
} // end of [public class Assign04_02{...}]
