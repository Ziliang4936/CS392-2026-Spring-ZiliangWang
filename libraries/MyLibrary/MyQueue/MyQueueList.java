package MyLibrary.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyQueueList<T> extends MyQueueBase<T> {

    int nitm = -1;
    Node frst = null;
    Node last = null;

    private class Node {
        private T item;
        private Node next;
        private Node(T itm, Node nxt) {
            item = itm;
            next = nxt;
        }
    }

    public MyQueueList() {
	nitm = 0; frst = null; last = null;
    }

    @Override
    public int size() {
	return nitm;
    }

    @Override
    public boolean isFull() {
	return false;
    }

    @Override
    public T top$raw() {
	return frst.item;
    }

    @Override
    public T deque$raw() {
	T itm = frst.item;
	frst = frst.next;
	if (frst == null) last = null;
	nitm -= 1; return itm;
    }

    @Override
    public void enque$raw(T itm) {
	if (last == null) {
	    last = new Node(itm, null);
	    frst = last;
	} else {
	    last.next = new Node(itm, null);
	    last = last.next;
	}
	nitm += 1; return;
    }

    @Override
    public void
	foritm(Consumer<? super T> work) {
	Node xs = frst;
	while (xs != null) {
	    work.accept(xs.item); xs = xs.next;
	}
    }

    @Override
    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int i = 0;
	Node xs = frst;
	while (xs != null) {
	    work.accept(i, xs.item); i += 1; xs = xs.next;
	}
    }

    @Override
    @SuppressWarnings("unchecked")
    public void rforitm(Consumer<? super T> work) {
	Object[] tmp = new Object[nitm];
	Node xs = frst; int i = 0;
	while (xs != null) { tmp[i++] = xs.item; xs = xs.next; }
	for (int j = i - 1; j >= 0; j--) { work.accept((T)tmp[j]); }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void irforitm(BiConsumer<Integer, ? super T> work) {
	Object[] tmp = new Object[nitm];
	Node xs = frst; int i = 0;
	while (xs != null) { tmp[i++] = xs.item; xs = xs.next; }
	for (int j = 0; j < i; j++) { work.accept(j, (T)tmp[i - 1 - j]); }
    }
}
