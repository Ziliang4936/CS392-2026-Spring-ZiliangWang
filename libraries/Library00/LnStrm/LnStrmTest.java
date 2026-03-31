//
import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnTuple.*;
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

    public static LnStrm<LnStrm<FnTupl2<Integer,Integer>>> genMatrix() {
      	   return LnStrmSUtil.map0(intFrom(1), (i) -> {
	        return LnStrmSUtil.map0(intFrom(1), (x) -> new FnTupl2<Integer,Integer>(i, x));
	      }
	    );
    }

    public static Integer cube_sum(Integer x, Integer y) {
       return x * x * x + y * y * y;
    }
    public static
       LnStrm<FnTupl2<Integer,Integer>>
       matrixEnum(LnStrm<LnStrm<FnTupl2<Integer,Integer>>> fxss) {
       return new LnStrm<FnTupl2<Integer,Integer>>(
         () -> {
           LnStcn<LnStrm<FnTupl2<Integer,Integer>>> cxss = fxss.eval0();
           LnStrm<FnTupl2<Integer,Integer>> frow1 = cxss.hd();
           LnStcn<FnTupl2<Integer,Integer>> crow1 = frow1.eval0();
           LnStrm<LnStrm<FnTupl2<Integer,Integer>>> frest = cxss.tl();
	   return new LnStcn<FnTupl2<Integer,Integer>>(
	     crow1.hd(),
	     LnStrmSUtil.m2erge0(
	       crow1.tl(), matrixEnum(frest), (x, y) -> {
	         return cube_sum(x.sub0, x.sub1).compareTo(cube_sum(y.sub0, y.sub1));
	       }
	     )
	   );
         }
       );
    }

    public static void main(String[] args) {
	LnStrm<Integer> intFrom2 = intFrom(2);
	LnStrm<Integer> thePrimes = sieveMethod(intFrom2);
	// thePrimes.foritm0((px) -> System.out.println(px));


	LnStrm<Integer> ints1 = intFrom(0);
	LnStrm<FnTupl2<Integer,Integer>> int2s1 =
	    LnStrmSUtil.map0(ints1, (x) -> new FnTupl2<Integer,Integer>(1, x));
	LnStrm<FnTupl2<Integer,Integer>> int2s2 =
	    LnStrmSUtil.map0(ints1, (x) -> new FnTupl2<Integer,Integer>(1, x));
	// int2s2.foritm0((xx) -> System.out.println(xx.toString()));

        LnStrm<FnTupl2<Integer,Integer>> int2s3 = matrixEnum(genMatrix());
	int2s3.foritm0((xx) -> System.out.println(xx.toString()));

	return;
    }
}
