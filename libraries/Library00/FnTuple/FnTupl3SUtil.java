package Library00.FnTuple;

public class FnTupl3SUtil {
    public static <T0,T1,T2>
	void System$out$print(FnTupl3<T0,T1,T2> tup) {
    	System.out.print("FnTupl3(");
	System.out.print(tup.sub0.toString());
	System.out.print(",");
	System.out.print(tup.sub1.toString());
	System.out.print(",");
	System.out.print(tup.sub2.toString());
	System.out.print(")");
    }
    public static
	<T0 extends Comparable<T0>,
	 T1 extends Comparable<T1>,
	 T2 extends Comparable<T2>>
        int compare(FnTupl3<T0,T1,T2> tup1, FnTupl3<T0,T1,T2> tup2) {
	int sgn;
	sgn = tup1.sub0.compareTo(tup2.sub0);
	if (sgn != 0)
	    return sgn;
	else {
	    sgn = tup1.sub1.compareTo(tup2.sub1);
	    if (sgn != 0)
		return sgn;
	    else
		return tup1.sub2.compareTo(tup2.sub2);
	} // end of [if]
    }
} // end of [public class FnTupl3SUtil{...}]
