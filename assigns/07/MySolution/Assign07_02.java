import Library00.LnStrm.*;
import Library00.FnTuple.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_02 {

    static int cubeSum(int x, int y) {
	return x * x * x + y * y * y;
    }

    static int cubeSumOf(FnTupl2<Integer,Integer> p) {
	return cubeSum(p.sub0, p.sub1);
    }

    static LnStrm<FnTupl2<Integer,Integer>> pairsFrom(int x, int y) {
	return new LnStrm<>(
	    () -> new LnStcn<>(
		new FnTupl2<>(x, y),
		pairsFrom(x, y + 1)
	    )
	);
    }

    static LnStrm<LnStrm<FnTupl2<Integer,Integer>>> allRows(int x) {
	return new LnStrm<>(
	    () -> new LnStcn<>(
		pairsFrom(x, x),
		allRows(x + 1)
	    )
	);
    }

    public static
	LnStrm<
	  FnTupl2<Integer,Integer>>
	cubeSumOrderedIntegerPairs() {
	return Assign07_01.mergeLnStrm(
	    allRows(1),
	    (a, b) -> Integer.compare(cubeSumOf(a), cubeSumOf(b))
	);
    }

    static LnStrm<Integer>
	findRamanujan(
	    FnTupl2<Integer,Integer> prev,
	    LnStrm<FnTupl2<Integer,Integer>> rest) {
	return new LnStrm<>(
	    () -> {
		FnTupl2<Integer,Integer> p = prev;
		LnStrm<FnTupl2<Integer,Integer>> r = rest;
		while (true) {
		    LnStcn<FnTupl2<Integer,Integer>> cn = r.eval0();
		    if (cn.nilq()) return new LnStcn<>();
		    FnTupl2<Integer,Integer> curr = cn.hd();
		    if (cubeSumOf(p) == cubeSumOf(curr)) {
			int ramNum = cubeSumOf(p);
			r = cn.tl();
			while (true) {
			    LnStcn<FnTupl2<Integer,Integer>> ck = r.eval0();
			    if (ck.nilq())
				return new LnStcn<>(ramNum, new LnStrm<>());
			    if (cubeSumOf(ck.hd()) != ramNum)
				return new LnStcn<>(ramNum, findRamanujan(ck.hd(), ck.tl()));
			    r = ck.tl();
			}
		    }
		    p = curr;
		    r = cn.tl();
		}
	    }
	);
    }

    public static
	LnStrm<Integer>
	ramanujanNumbers() {
	LnStrm<FnTupl2<Integer,Integer>> pairs = cubeSumOrderedIntegerPairs();
	LnStcn<FnTupl2<Integer,Integer>> c = pairs.eval0();
	return findRamanujan(c.hd(), c.tl());
    }

    public static void main(String[] args) {
	System.out.println("=== First 20 pairs by cube sum ===");
	LnStrm<FnTupl2<Integer,Integer>> pairs = cubeSumOrderedIntegerPairs();
	final int[] count = {0};
	pairs.forall0((p) -> {
	    System.out.println(p.toString() + " => " + cubeSumOf(p));
	    count[0] += 1;
	    return count[0] < 20;
	});

	System.out.println("\n=== Ramanujan numbers ===");
	LnStrm<Integer> rams = ramanujanNumbers();
	final int[] count2 = {0};
	rams.forall0((n) -> {
	    System.out.println(n);
	    count2[0] += 1;
	    return count2[0] < 10;
	});
    }

} // end of [public class Assign07_02{...}]

