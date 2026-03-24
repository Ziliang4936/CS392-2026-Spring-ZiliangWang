import java.util.Random;
import java.util.function.ToIntBiFunction;

class QuickSortArray {
    public static
	<T extends Comparable<T>>
	void quickSort_array(T[] xs) {
	quickSort_array(xs, (x1, x2) -> x1.compareTo(x2));
	return;
    }
    public static<T>
	void quickSort_array(T[] xs, ToIntBiFunction<T,T> cmp) {
	quickSort_arrayseg_rand(xs, 0, xs.length-1, cmp, new Random());
	return;
    }
    private static<T>
	void arrayseg_swap(T[] xs, int i, int j) {
	T tmp = xs[i]; xs[i] = xs[j]; xs[j] = tmp;
    }
    private static<T>
	int arrayseg_pivot(T[] xs, int ia, int iz, ToIntBiFunction<T,T> cmp) {
	return 0; // HX: this one needs to be FIXED!!!
    }
    private static<T>
	void quickSort_arrayseg_rand(T[] xs, int ia, int iz, ToIntBiFunction<T,T> cmp, Random rand) {
	int ln = iz-ia+1;
	if (ln <= 1) return; // HX: xs contains at most one element!

	int pvt = rand.nextInt() % ln;
	pvt = (pvt >= 0) ? pvt : (ln+pvt); // HX: 0 <= pvt <= ln-1
	assert(0 <= pvt && pvt <= ln-1);
	// System.out.println("quickSort_arrayseg_rand: pvt = " + pvt);

	arrayseg_swap(xs, ia+pvt, iz);

	int im = arrayseg_pivot(xs, ia, iz, cmp);

	if (ia+iz >= 2*im) {
	    quickSort_arrayseg_rand(xs, ia, im-1, cmp, rand);
	    quickSort_arrayseg_rand(xs, im+1, iz, cmp, rand);
	} else {
	    quickSort_arrayseg_rand(xs, im+1, iz, cmp, rand);
	    quickSort_arrayseg_rand(xs, ia, im-1, cmp, rand);
	}
	return;
    }
}
