package Library00.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyQueueArray<T> extends MyQueueBase<T> {

    int nitm = -1;
    int frst = -1;
    int last = -1;
    T[] itms = null;

    public
    MyQueueArray(int cap)
    {
	assert (cap >= 2);
	nitm = 0;
	frst = 0; last = 0;
	itms = (T[]) new Object[cap];
    }
    
    @Override
    public int size() {
	return nitm;
    }

    @Override
    public boolean isFull() {
	return (nitm >= itms.length);
    }

    @Override
    public T top$raw() {
	return itms[frst];
    }

    @Override
    public T deque$raw() {
	T itm = itms[frst];
	nitm -= 1;
	/*
	  frst += 1;
	  if (frst >= itms.length) frst = 0;
	*/
	frst = (frst + 1) % itms.length;
	return itm;
    }

    @Override
    public void enque$raw(T itm) {
	itms[last] = itm;
	nitm += 1;
	/*
	  last += 1;
	  if (last >= itms.length) last = 0;
	*/
	last = (last + 1) % itms.length;
	return /*void*/ ;
    }

    @Override
    public void
	foritm(Consumer<? super T> work) {
	int n = itms.length;
	for (int i = 0; i < nitm; i += 1) {
	    work.accept(itms[(frst+i)%n]);
	}
	return /*void*/ ;
    }

    @Override
    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int n = itms.length;
	for (int i = 0; i < nitm; i += 1) {
	    work.accept(i, itms[(frst+i)%n]);
	}
	return /*void*/ ;
    }

    @Override
    public void
	rforitm(Consumer<? super T> work) {
	int n = itms.length;
	for (int i = 0; i < nitm; i += 1) {
	    work.accept(itms[(last-1-i)%n]);
	}
	return /*void*/ ;
    }

    @Override
    public void
	irforitm(BiConsumer<Integer, ? super T> work) {
	int n = itms.length;
	for (int i = 0; i < nitm; i += 1) {
	    work.accept(i, itms[(last-1-i)%n]);
	}
	return /*void*/ ;
    }

}
