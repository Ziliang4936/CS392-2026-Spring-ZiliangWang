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
	FnList<T> toFnList0(LnStrm<T> fxs) {
	return FnListSUtil.fwork$make((work) -> fxs.foritm0(work));
    }
//
} // end of [class LnStrmSUtil{...}]
