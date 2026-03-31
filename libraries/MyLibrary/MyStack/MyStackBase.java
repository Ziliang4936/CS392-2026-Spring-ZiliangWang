package MyLibrary.MyStack;

import MyLibrary.FnList.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyStackBase<T> implements MyStack<T> {
//
    public
    boolean isEmpty()
    {
	return (size() <= 0);
    }
//
    public T top$opt() {
	return (isEmpty() ? null : top$raw());
    }
    public T top$exn() throws MyStackEmptyExn {
	T top = top$opt();
	if (top != null) return top; else throw new MyStackEmptyExn();
    }
//
    public T pop$opt() {
	return (isEmpty() ? null : pop$raw());
    }
    public T pop$exn() throws MyStackEmptyExn {
	T pop = pop$opt();
	if (pop != null) return pop; else throw new MyStackEmptyExn();
    }
//
    public boolean push$opt(T itm) {
	if (isFull()) {
	    return false;
	} else {
	    push$raw(itm); return true;
	}
    }

    public void push$exn(T itm) throws MyStackFullExn {
        boolean res = push$opt(itm);
	if (!res) throw new MyStackFullExn(); else return;
    }
//
    public void System$out$print() {
    	System.out.print("MyStack(");
	this.iforitm
	(
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    @SuppressWarnings("unchecked")
    public void
    rforitm(Consumer<? super T> work) {
        final FnList<T>[] ref = new FnList[]{new FnList<T>()};
	foritm(
	  itm -> ref[0] = new FnList<T>(itm, ref[0])
        );
	(ref[0]).foritm(work);
    }
//
    @SuppressWarnings("unchecked")
    public void
    irforitm(BiConsumer<Integer, ? super T> work) {
        final FnList<T>[] ref = new FnList[]{new FnList<T>()};
	foritm(
	  itm -> ref[0] = new FnList<T>(itm, ref[0])
        );
	(ref[0]).iforitm(work);
    }
//
} // end of [public abstract class MyStackBase<T>{...}]
