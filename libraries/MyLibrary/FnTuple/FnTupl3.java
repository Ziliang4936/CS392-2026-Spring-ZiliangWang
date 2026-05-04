package Library00.FnTuple;

public class FnTupl3<T0,T1,T2> {
    public T0 sub0;
    public T1 sub1;
    public T2 sub2;
    public
    FnTupl3(T0 x0, T1 x1, T2 x2) {
	sub0 = x0; sub1 = x1; sub2 = x2;
    }
    public void System$out$print() {
	FnTupl3SUtil.System$out$print(this);
    }
    public String toString() {
	return "FnTupl3(" + sub0.toString() + "," + sub1.toString() + "," + sub2.toString() + ")";
    }
} // end of [public class FnTupl3<T0,T1,T2>{...}]
