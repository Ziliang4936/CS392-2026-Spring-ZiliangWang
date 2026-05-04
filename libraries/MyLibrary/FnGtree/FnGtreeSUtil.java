package MyLibrary.FnGtree;

import MyLibrary.FnList.*;
import MyLibrary.MyStack.*;
import MyLibrary.MyQueue.*;

import java.util.function.Consumer;

public class FnGtreeSUtil {
//
    public static<T>
	void BFirstSearch
	(FnGtree<T> root, Consumer<? super T> work) {
	FnGtree<T> node;
	MyQueueList<FnGtree<T>> queue = new MyQueueList<FnGtree<T>>();
	queue.enque$raw(root);
	while (!queue.isEmpty()) {
	    node = queue.deque$raw();
	    work.accept(node.value());
	    FnList<FnGtree<T>> cs = node.children();
	    while (cs.consq()) {
		queue.enque$raw(cs.hd()); cs = cs.tl();
	    }
	}
	return;
    }
//
    public static<T>
	void DFirstSearch
	(FnGtree<T> root, Consumer<? super T> work) {
	FnGtree<T> node;
	MyStackList<FnGtree<T>> stack = new MyStackList<FnGtree<T>>();
	stack.push$raw(root);
	while (!stack.isEmpty()) {
	    node = stack.pop$raw();
	    work.accept(node.value());
	    FnList<FnGtree<T>> cs = node.children();
	    FnList<FnGtree<T>> rev = FnListSUtil.<FnGtree<T>>nil();
	    while (cs.consq()) {
		rev = new FnList<>(cs.hd(), rev); cs = cs.tl();
	    }
	    while (rev.consq()) {
		stack.push$raw(rev.hd()); rev = rev.tl();
	    }
	}
	return;
    }
//
} // end of [public class FnGtreeSUtil{...}]
