import Library00.LnStrm.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_01 {
//
    public static<T>
	LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
	return new LnStrm<T>(
	    () -> {
		LnStcn<LnStrm<T>> cxss = fxss.eval0();
		if (cxss.nilq()) {
		    return new LnStcn<T>();
		}
		LnStrm<T> frow = cxss.hd();
		LnStcn<T> crow = frow.eval0();
		if (crow.nilq()) {
		    return mergeLnStrm(cxss.tl(), cmpr).eval0();
		}
		return new LnStcn<T>(
		    crow.hd(),
		    LnStrmSUtil.m2erge0(
			crow.tl(),
			mergeLnStrm(cxss.tl(), cmpr),
			cmpr
		    )
		);
	    }
	);
    }
//

    public static void main(String[] args) {
	LnStrm<LnStrm<Integer>> streams =
	    LnStrmSUtil.map0(
		intFrom(1),
		(x) -> LnStrmSUtil.map0(intFrom(0), (y) -> x * 10 + y)
	    );
	LnStrm<Integer> merged =
	    mergeLnStrm(streams, (a, b) -> Integer.compare(a, b));
	final int[] count = {0};
	merged.forall0((x) -> {
	    System.out.println(x);
	    count[0] += 1;
	    return count[0] < 30;
	});
    }

    static LnStrm<Integer> intFrom(int start) {
	return new LnStrm<Integer>(
	    () -> new LnStcn<Integer>(start, intFrom(start + 1))
	);
    }

} // end of [public class Assign07_01{...}]

