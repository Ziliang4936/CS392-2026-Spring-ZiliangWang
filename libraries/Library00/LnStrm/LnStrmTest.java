//
import Library00.FnList.*;
import Library00.LnStrm.*;
//
import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;
//
class LnStrmTest {
    public static LnStrm<Integer> intFrom(int start) {
	return new LnStrm<Integer>(() -> new LnStcn<Integer>(start, intFrom(start+1)));
    }
    public static LnStrm<Integer> sieveMethod(LnStrm<Integer> fxs) {
	LnStcn<Integer> cxs = fxs.eval0();
	Integer hd = cxs.hd();
	LnStrm<Integer> tl = cxs.tl();
	return new LnStrm<Integer>
	    (() -> new LnStcn<Integer>(hd, sieveMethod(tl.filter0((ix) -> ix.intValue() % hd > 0))));
    }
    public static void main(String[] args) {
	LnStrm<Integer> intFrom2 = intFrom(2);
	LnStrm<Integer> thePrimes = sieveMethod(intFrom2);
	thePrimes.foritm0((px) -> System.out.println(px));
	return;
    }
}
