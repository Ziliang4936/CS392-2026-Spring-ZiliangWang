//
// HX: 20 points
//
// MyLibrary contents (top-level classes in libraries/MyLibrary):
//   FnList.FnList, FnList.FnListSUtil
//   FnA1sz.FnA1sz, FnA1sz.FnA1szSUtil
//   FnStrn.FnStrn, FnStrn.FnStrnSUtil
//   MyStack.MyStackBase, MyStack.MyStackList, MyStack.MyStackArray,
//          MyStack.MyStackEmptyExn, MyStack.MyStackFullExn
//   MyQueue.MyQueueBase, MyQueue.MyQueueArray,
//          MyQueue.MyQueueEmptyExn, MyQueue.MyQueueFullExn
//
// (MyStack and MyQueue are package-private interfaces, so they are
// shown by instantiating the classes that implement them.)
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnStrn.*;
import MyLibrary.MyStack.*;
import MyLibrary.MyQueue.*;

public class Quiz02_00 {
    public static void main (String[] args) {
	// FnList
	FnList<Integer> FnList_Integer_obj = new FnList<Integer>();
	FnListSUtil FnListSUtil_obj = new FnListSUtil();
	System.out.println("FnList_Integer_obj.nilq() = "
			   + FnList_Integer_obj.nilq());
	System.out.println("FnListSUtil_obj = " + FnListSUtil_obj);

	// FnA1sz
	FnA1sz<Integer> FnA1sz_Integer_obj =
	    new FnA1sz<Integer>(new Integer[] {1, 2, 3});
	FnA1szSUtil FnA1szSUtil_obj = new FnA1szSUtil();
	System.out.println("FnA1sz_Integer_obj.length() = "
			   + FnA1sz_Integer_obj.length());
	System.out.println("FnA1szSUtil_obj = " + FnA1szSUtil_obj);

	// FnStrn
	FnStrn FnStrn_obj = new FnStrn("Hello");
	FnStrnSUtil FnStrnSUtil_obj = new FnStrnSUtil();
	System.out.println("FnStrn_obj.length() = " + FnStrn_obj.length());
	System.out.println("FnStrnSUtil_obj = " + FnStrnSUtil_obj);

	// MyStack family
	MyStackList<Integer> MyStackList_obj = new MyStackList<Integer>();
	MyStackArray<Integer> MyStackArray_obj = new MyStackArray<Integer>(8);
	MyStackEmptyExn MyStackEmptyExn_obj = new MyStackEmptyExn();
	MyStackFullExn MyStackFullExn_obj = new MyStackFullExn();
	System.out.println("MyStackList_obj.size() = " + MyStackList_obj.size());
	System.out.println("MyStackArray_obj.size() = " + MyStackArray_obj.size());
	System.out.println("MyStackEmptyExn_obj = " + MyStackEmptyExn_obj);
	System.out.println("MyStackFullExn_obj = " + MyStackFullExn_obj);

	// MyQueue family
	MyQueueArray<Integer> MyQueueArray_obj = new MyQueueArray<Integer>(8);
	MyQueueEmptyExn MyQueueEmptyExn_obj = new MyQueueEmptyExn();
	MyQueueFullExn MyQueueFullExn_obj = new MyQueueFullExn();
	System.out.println("MyQueueArray_obj.size() = " + MyQueueArray_obj.size());
	System.out.println("MyQueueEmptyExn_obj = " + MyQueueEmptyExn_obj);
	System.out.println("MyQueueFullExn_obj = " + MyQueueFullExn_obj);
    }
} // end of [class Quiz02_00{...}]
