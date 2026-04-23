import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyPQueueArray<T extends Comparable<T>> extends MyPQueueBase<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyPQueueArray(int capacity) {
	data = (T[])new Comparable[capacity];
	size = 0;
    }

    private void swap(int i, int j) {
	T tmp = data[i];
	data[i] = data[j];
	data[j] = tmp;
    }

    // Restore the heap order after insertion.
    private void bubbleUp(int i) {
	while (i > 0) {
	    int p = (i - 1) / 2;
	    if (data[p].compareTo(data[i]) >= 0) {
		break;
	    }
	    swap(p, i);
	    i = p;
	}
    }

    // Restore the heap order after deletion.
    private void bubbleDown(int i) {
	while (true) {
	    int l = 2 * i + 1;
	    int r = 2 * i + 2;
	    int largest = i;
	    if (l < size && data[l].compareTo(data[largest]) > 0) {
		largest = l;
	    }
	    if (r < size && data[r].compareTo(data[largest]) > 0) {
		largest = r;
	    }
	    if (largest == i) {
		break;
	    }
	    swap(i, largest);
	    i = largest;
	}
    }

    public int size() {
	return size;
    }

    public boolean isFull() {
	return size >= data.length;
    }

    public T top$raw() {
	return data[0];
    }

    public T deque$raw() {
	T top = data[0];
	size -= 1;
	if (size > 0) {
	    data[0] = data[size];
	    bubbleDown(0);
	}
	data[size] = null;
	return top;
    }

    public void enque$raw(T itm) {
	data[size] = itm;
	bubbleUp(size);
	size += 1;
    }

    public static void main(String[] args) {
	// Minimal test cases for enqueue and dequeue.
	MyPQueueArray<Integer> pq = new MyPQueueArray<>(10);
	System.out.println("isEmpty = " + pq.isEmpty());
	pq.enque$raw(5);
	pq.enque$raw(1);
	pq.enque$raw(9);
	pq.enque$raw(3);
	pq.enque$raw(7);
	System.out.println("size = " + pq.size());
	System.out.println("top = " + pq.top$raw());
	System.out.println("deque = " + pq.deque$raw());
	System.out.println("deque = " + pq.deque$raw());
	System.out.println("top after deque = " + pq.top$raw());
	System.out.println("enque$opt(8) = " + pq.enque$opt(8));
	System.out.println("deque sequence:");
	while (!pq.isEmpty()) {
	    System.out.println(pq.deque$raw());
	}

	MyPQueueArray<Integer> small = new MyPQueueArray<>(2);
	small.enque$raw(4);
	small.enque$raw(6);
	System.out.println("small isFull = " + small.isFull());
	System.out.println("enque$opt on full queue = " + small.enque$opt(10));
	try {
	    small.enque$exn(10);
	} catch (MyPQueueFullExn e) {
	    System.out.println("MyPQueueFullExn caught");
	}
	try {
	    (new MyPQueueArray<Integer>(1)).deque$exn();
	} catch (MyPQueueEmptyExn e) {
	    System.out.println("MyPQueueEmptyExn caught");
	}
    }
}
