package MyLibrary.LnList;

import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class LnListSUtil {
//
    public static<T>
	LnList<T> nil() {
	return new LnList<T>();
    }
    public static<T>
	LnList<T>
	cons(T x0, LnList<T> xs) {
	return new LnList<T>(x0, xs);
    }
//
    public static<T>
	boolean nilq1(LnList<T> xs) {
	return xs.nilq1();
    }
    public static<T>
	boolean consq1(LnList<T> xs) {
	return xs.consq1();
    }
//
    public static<T>
	LnList<T> reverse0(LnList<T> xs) {
	return xs.reverse0();
    }
//
    public static<T>
	LnList<T>
	insertSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq1()) return xs;
	LnList<T> unsorted = xs.unlink1();
	LnList<T> sorted = xs;
	LnList<T> sortedTail = sorted;
	while (unsorted.consq1()) {
	    LnList<T> next = unsorted.unlink1();
	    LnList<T> node = unsorted;
	    unsorted = next;
	    T val = node.hd1();
	    if (cmp.applyAsInt(val, sorted.hd1()) < 0) {
		node.link1(sorted);
		sorted = node;
	    } else if (cmp.applyAsInt(val, sortedTail.hd1()) >= 0) {
		sortedTail.unlink1();
		sortedTail.link1(node);
		sortedTail = node;
	    } else {
		LnList<T> prev = sorted;
		LnList<T> curr = prev.tl1();
		while (curr.consq1() && cmp.applyAsInt(val, curr.hd1()) >= 0) {
		    prev = curr;
		    curr = curr.tl1();
		}
		LnList<T> tail = prev.unlink1();
		node.link1(tail);
		prev.link1(node);
	    }
	}
	return sorted;
    }
//
    public static<T>
	LnList<T>
	mergeSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n = xs.length1();
	if (n <= 1) return xs;
	int mid = n / 2;
	LnList<T> left = nil();
	LnList<T> right = xs;
	for (int i = 0; i < mid; i += 1) {
	    left = cons(right.hd1(), left);
	    right = right.tl1();
	}
	left = reverse0(left);
	left = mergeSort(left, cmp);
	right = mergeSort(right, cmp);
	return mergeLists(left, right, cmp);
    }
    private static<T>
	LnList<T>
	mergeLists(LnList<T> xs, LnList<T> ys, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq1()) return ys;
	if (ys.nilq1()) return xs;
	if (cmp.applyAsInt(xs.hd1(), ys.hd1()) <= 0) {
	    return cons(xs.hd1(), mergeLists(xs.tl1(), ys, cmp));
	} else {
	    return cons(ys.hd1(), mergeLists(xs, ys.tl1(), cmp));
	}
    }
//
} // end of [public class LnListSUtil{...}]
