//
// HX: 10 points
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnStrn.*;
import MyLibrary.MyStack.*;
import MyLibrary.MyQueue.*;

public class Quiz01_00 {
    /*
     MyLibrary contains the following classes:
     1. FnList<T>       - Functional (immutable-style) singly-linked list
     2. FnListSUtil      - Static utilities for FnList (nil, cons, reverse, append, insertSort, mergeSort, ...)
     3. FnA1sz<T>       - Functional array (backed by T[])
     4. FnA1szSUtil      - Static utilities for FnA1sz (map, fold, sort, ...)
     5. FnStrn           - Functional string (backed by char[])
     6. FnStrnSUtil      - Static utilities for FnStrn (reverse, foritm, forall, ...)
     7. MyStackArray<T>  - Stack implemented with an array
     8. MyStackList<T>   - Stack implemented with a linked list
     9. MyQueueArray<T>  - Queue implemented with a circular array
     */
    public static void main (String[] args) {
	// FnList
	FnList<Integer> FnList_Integer_obj = new FnList<Integer>();
	FnList<String> FnList_String_obj = new FnList<String>("hello", new FnList<String>());
	System.out.print("FnList<Integer>: ");
	FnListSUtil.System$out$print(FnList_Integer_obj);
	System.out.println();
	System.out.print("FnList<String>:  ");
	FnListSUtil.System$out$print(FnList_String_obj);
	System.out.println();

	// FnA1sz
	FnA1sz<Integer> FnA1sz_Integer_obj = FnA1szSUtil.int1$make(5);
	System.out.print("FnA1sz<Integer>: ");
	FnA1sz_Integer_obj.System$out$print();
	System.out.println();

	// FnStrn
	FnStrn FnStrn_obj = new FnStrn("CS392");
	System.out.print("FnStrn:          ");
	System.out.println("FnStrn(\"CS392\"), length=" + FnStrn_obj.length());

	// MyStackArray
	MyStackArray<Integer> MyStackArray_obj = new MyStackArray<Integer>(10);
	MyStackArray_obj.push$raw(1);
	MyStackArray_obj.push$raw(2);
	MyStackArray_obj.push$raw(3);
	System.out.print("MyStackArray:    ");
	MyStackArray_obj.System$out$print();
	System.out.println();

	// MyStackList
	MyStackList<Integer> MyStackList_obj = new MyStackList<Integer>();
	MyStackList_obj.push$raw(10);
	MyStackList_obj.push$raw(20);
	MyStackList_obj.push$raw(30);
	System.out.print("MyStackList:     ");
	MyStackList_obj.System$out$print();
	System.out.println();

	// MyQueueArray
	MyQueueArray<Integer> MyQueueArray_obj = new MyQueueArray<Integer>(10);
	MyQueueArray_obj.enque$raw(100);
	MyQueueArray_obj.enque$raw(200);
	MyQueueArray_obj.enque$raw(300);
	System.out.print("MyQueueArray:    ");
	MyQueueArray_obj.System$out$print();
	System.out.println();

	return /*void*/;
    }
}
