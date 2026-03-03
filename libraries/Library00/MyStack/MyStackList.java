package Library.MyStack;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyStackList<T> extends MyStackBase<T> {

    int nitm = -1;
    Node itms = null;

    private class Node {
        private T item;
        private Node next;
        
        private Node(T itm, Node nxt) {
            item = itm;
            next = nxt;
        }
    }

    public MyStackList() {
	nitm = 0; itms = null;
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
	return itms.item;
    }

    @Override
    public T pop$raw() {
	T itm = itms.item;
	itms = itms.next;
	nitm -= 1; return itm;
    }

    @Override
    public void push$raw(T itm) {
	itms = new Node(itm, itms);
	nitm += 1; return;
    }

    @Override
    public void foritm(Consumer<? super T> work) {
	Node xs = itms;
	while (xs != null) {
	    work.accept(xs.item); xs = xs.next;
	}
    }

    @Override
    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int i = 0;
	Node xs = itms;
	while (xs != null) {
	    work.accept(i, xs.item); i += 1; xs = xs.next;
	}
    }
}
