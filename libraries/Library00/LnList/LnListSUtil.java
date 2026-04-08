package Library00.LnList;

import Library00.FnList.*;
import Library00.FnA1sz.*;

import java.util.Random;
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
} // end of [public class LnListSUtil{...}]
