public abstract class MyPQueueBase<T> implements MyPQueue<T> {
//
    public
    boolean isEmpty()
    {
	return (size() <= 0);
    }
//
    public T top$opt() {
	return
	  (isEmpty()?null:top$raw());
    }
    public T top$exn() throws MyPQueueEmptyExn {
	T top = top$opt();
	if (top != null)
	    return top;
	else {
	    throw new MyPQueueEmptyExn();
	}
    }
//
    public T deque$opt() {
	return
	  (isEmpty()?null:deque$raw());
    }
    public T deque$exn() throws MyPQueueEmptyExn {
	T deque = deque$opt();
	if (deque != null)
	    return deque;
	else {
	    throw new MyPQueueEmptyExn();
	}
    }
//
    public boolean enque$opt(T itm) {
	if (isFull()) {
	    return false;
	} else {
	    enque$raw(itm); return true;
	}
    }

    public void enque$exn(T itm) throws MyPQueueFullExn {
        boolean res = enque$opt(itm);
	if (!res) throw new MyPQueueFullExn(); else return;
    }
//
} // end of [public abstract class MyPQueueBase<T>{...}]
