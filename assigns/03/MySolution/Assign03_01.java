/*
HX-2026-02-05: 10 points
*/
public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    //
    /*
    static int f91(int n) {
	if (n > 100)
	    return n-10;
	else
	    return f91(f91(n+11));
    }
    */
    // Tail-recursive: helper carries 'depth' = how many more f91 applications needed
    static int f91_tail(int n, int depth) {
	if (n > 100) {
	    int r = n - 10;
	    if (depth == 0)
		return r;
	    return f91_tail(r, depth - 1);
	} else {
	    return f91_tail(n + 11, depth + 1);
	}
    }
    static int f91(int n) {
	return f91_tail(n, 0);
    }

    public static void main(String[] argv) {
    // please write some testing code here
	// Testing McCarthy 91: f91(n)=91 for n<=100, f91(n)=n-10 for n>100
	System.out.println("f91(100) = " + f91(100));   // 91
	System.out.println("f91(99)  = " + f91(99));    // 91
	System.out.println("f91(50)  = " + f91(50));    // 91
	System.out.println("f91(101) = " + f91(101));   // 91
	System.out.println("f91(150) = " + f91(150));   // 140
	System.out.println("f91(200) = " + f91(200));   // 190
    }
}
