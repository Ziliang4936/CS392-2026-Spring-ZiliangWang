package Library00.FnGtree;

import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.MyStack.*;
import Library00.MyQueue.*;

import java.util.function.Consumer;

public class FnGtreeSUtil {
//
    public static<T>
	void BFirstSearch
	(FnGtree<T> root, Consumer<? super T> work) {
	FnGtree<T> node;
	MyQueueList<FnGtree<T>> queue = new MyQueueList<FnGtree<T>>();
	queue.enque$exn(root);
	while (true) {
	    if (queue.isEmpty()) break;
	    node = queue.deque$raw();
	    work.accept(node.value());
	    node.children().foritm((tx) -> queue.enque$exn(tx));
	}
	return;
    }
//
    public static<T> LnStrm<T>
	BFirstEnumerate(FnGtree<T> root) {
	MyQueueList<FnGtree<T>>
	queue = new MyQueueList<FnGtree<T>>();
	queue.enque$exn(root); return BFirstEnumerate_helper(queue);
    }
    private static<T> LnStrm<T>
	BFirstEnumerate_helper(MyQueueList<FnGtree<T>> queue) {
	return new LnStrm<T>(
	  () -> {
	      if (queue.isEmpty()) {
		  return new LnStcn<T>();
	      } else {
		  FnGtree<T> node = queue.deque$raw();
		  node.children().foritm((tx) -> queue.enque$exn(tx));
		  return new LnStcn<T>(node.value(), BFirstEnumerate_helper(queue));
	      }
	  }
        );
    }
//
    public static<T>
	void DFirstSearch
	(FnGtree<T> root, Consumer<? super T> work) {
	FnGtree<T> node;
	MyStackList<FnGtree<T>> stack = new MyStackList<FnGtree<T>>();
	stack.push$exn(root);
	while (true) {
	    if (stack.isEmpty()) break;
	    node = stack.pop$raw();
	    work.accept(node.value());
	    node.children().rforitm((tx) -> stack.push$exn(tx));
	}
	return;
    }
//
    public static<T> LnStrm<T>
	DFirstEnumerate(FnGtree<T> root) {
	MyStackList<FnGtree<T>>
	stack = new MyStackList<FnGtree<T>>();
	stack.push$exn(root); return DFirstEnumerate_helper(stack);
    }
    private static<T> LnStrm<T>
	DFirstEnumerate_helper(MyStackList<FnGtree<T>> stack) {
	return new LnStrm<T>(
	  () -> {
	      if (stack.isEmpty()) {
		  return new LnStcn<T>();
	      } else {
		  FnGtree<T> node = stack.pop$raw();
		  node.children().foritm((tx) -> stack.push$exn(tx));
		  return new LnStcn<T>(node.value(), DFirstEnumerate_helper(stack));
	      }
	  }
        );
    }
//
} // end of [public class FnGtreeSUtil{...}]
