//
package Library00.LnStrm;
//
import Library00.FnList.*;
//
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.function.ToIntBiFunction;
//
// HX-2025-10-23:
// typedef LnStrm<T> = Supplier<LnStcn<T>>
//
public class LnStrmSUtil {

    public static<T>
	LnStcn<T> eval0(LnStrm<T> fxs) {
	return fxs.eval0();
    }
//
    public static<T>
	LnStrm<T> nil0() {
	return new LnStrm<T>(() -> new LnStcn<T>());
    }
    public static<T>
	LnStrm<T> cons0(T x0, LnStrm<T> fxs) {
	return new LnStrm<T>(() -> new LnStcn<T>(x0, fxs));
    }
//
    public static<T>
	LnStrm<T> append0
	(LnStrm<T> fxs, LnStrm<T> fys) {
	return new LnStrm<T>(
	  () -> {
	      final LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return fys.eval0();
	      } else {
		  return new LnStcn<T>(cxs.hd(), append0(cxs.tl(), fys));
	      }
	  }
       );
    }
//
    public static<T>
	void foritm0
	(LnStrm<T> fxs, Consumer<? super T> work) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    work.accept(cxs.hd()); cxs = cxs.tl().eval0();
	}
	return /*void*/;
    }
//
    public static<T>
	boolean forall0
	(LnStrm<T> fxs, Predicate<? super T> pred) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    if (!pred.test(cxs.hd()))
		return false;
	    else {
		cxs = cxs.tl().eval0(); continue;
	    }
	}
	return true; // all satisfy
    }
//
    public static<T,R>
	LnStrm<R> map0
	(LnStrm<T> fxs, Function<? super T, R> fopr) {
	return new LnStrm<R>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return new LnStcn<R>();
	      } else {
		  final T hd = cxs.hd();
		  final LnStrm<T> tl = cxs.tl();
		  return new LnStcn<R>(fopr.apply(hd), map0(tl, fopr));
	      }
	  }
       );
    }
//
    public static<T>
	LnStrm<T> filter0
	(LnStrm<T> fxs, Predicate<? super T> pred) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      while (cxs.consq()) {
		  final T hd = cxs.hd();
		  final LnStrm<T> tl = cxs.tl();
		  if (pred.test(hd)) {
		      return new LnStcn<T>(hd, filter0(tl, pred));
		  } else {
		      cxs = cxs.tl().eval0();
		  }
	      }
	      return new LnStcn<T>(); // no satisfying elements found
	  }
       );
    }
//
    public static<T>
	LnStrm<T> m2erge0
	(LnStrm<T> fxs, LnStrm<T> fys, ToIntBiFunction<T,T> cmp) {
	return new LnStrm<T>(
	  () -> {
  	    LnStcn<T> cxs = fxs.eval0();
	    if (cxs.nilq()) return fys.eval0();
	    LnStcn<T> cys = fys.eval0();
	    if (cys.nilq()) {
              return new LnStcn<T>(cxs.hd(), cxs.tl());
	    } else {
	      return m2erge0_helper(
	        cxs.hd(), cxs.tl(), cys.hd(), cys.tl(), cmp).eval0();
	    }
	  }
	);
    }
    private static<T>
    	LnStrm<T> m2erge0_helper
	(T hdx, LnStrm<T> gxs, T hdy, LnStrm<T> gys, ToIntBiFunction<T,T> cmp) {
	return new LnStrm<T>(
	  () -> {
	     T hd1 = hdx;
	     T hd2 = hdy;
	     LnStrm<T> fxs = gxs;
	     LnStrm<T> fys = gys;
	     if (hd1 == null) {
	       LnStcn<T> cxs = fxs.eval0();
	       if (cxs.nilq()) {
	         return new LnStcn<T>(hd2, fys);
	       } else {
	         hd1 = cxs.hd(); fxs = cxs.tl();
	       }
	     }
	     if (hd2 == null) {
	       LnStcn<T> cys = fys.eval0();
	       if (cys.nilq()) {
	         return new LnStcn<T>(hd1, fxs);
	       } else {
	         hd2 = cys.hd(); fys = cys.tl();
	       }
	     }
	     int sgn = cmp.applyAsInt(hd1, hd2);
	     if (sgn <= 0) {
	       T hd0 = hd1; hd1 = null;
	       return new LnStcn<T>(hd0, m2erge0_helper(hd1, fxs, hd2, fys, cmp));
	     } else {
	       T hd0 = hd2; hd2 = null;
	       return new LnStcn<T>(hd0, m2erge0_helper(hd1, fxs, hd2, fys, cmp));
	     }
	  }
        );
    }
//
} // end of [class LnStrmSUtil{...}]
