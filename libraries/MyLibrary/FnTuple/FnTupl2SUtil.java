package Library00.FnTuple;

public class FnTupl2SUtil {
//
    public static <T0,T1>
	void System$out$print(FnTupl2<T0,T1> tup) {
    	System.out.print("FnTupl2(");
	System.out.print(tup.sub0.toString());
	System.out.print(",");
	System.out.print(tup.sub1.toString());
	System.out.print(")");
    }
//
    public static
	<T0 extends Comparable<T0>,
	 T1 extends Comparable<T1>>
	int compare(FnTupl2<T0,T1> tup1, FnTupl2<T0,T1> tup2) {
	int sgn;
	sgn = tup1.sub0.compareTo(tup2.sub0);
	if (sgn != 0)
	    return sgn;
	else {
	    return tup1.sub1.compareTo(tup2.sub1);
	} // end of [if]
    }
//
} // end of [public class FnTupl2SUtil{...}]
