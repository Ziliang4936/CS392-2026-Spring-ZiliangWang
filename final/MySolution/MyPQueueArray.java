// HX: Verbatim copy of assigns/10/MySolution/MyPQueueArray.java
//     (max-heap on Comparable<T>). Final_05 wraps merge entries with a
//     reversed compareTo so this acts as a stable min-heap on cmp.

public class MyPQueueArray<T extends Comparable<T>> extends MyPQueueBase<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyPQueueArray(int capacity) {
	data = (T[])new Comparable[capacity];
	size = 0;
    }

    private void swap(int i, int j) {
	T tmp = data[i]; data[i] = data[j]; data[j] = tmp;
    }

    private void bubbleUp(int i) {
	while (i > 0) {
	    int p = (i - 1) / 2;
	    if (data[p].compareTo(data[i]) >= 0) break;
	    swap(p, i); i = p;
	}
    }

    private void bubbleDown(int i) {
	while (true) {
	    int l = 2 * i + 1;
	    int r = 2 * i + 2;
	    int largest = i;
	    if (l < size && data[l].compareTo(data[largest]) > 0) largest = l;
	    if (r < size && data[r].compareTo(data[largest]) > 0) largest = r;
	    if (largest == i) break;
	    swap(i, largest); i = largest;
	}
    }

    public int size() { return size; }
    public boolean isFull() { return size >= data.length; }

    public T top$raw() { return data[0]; }

    public T deque$raw() {
	T top = data[0];
	size -= 1;
	if (size > 0) { data[0] = data[size]; bubbleDown(0); }
	data[size] = null;
	return top;
    }

    public void enque$raw(T itm) {
	data[size] = itm;
	bubbleUp(size);
	size += 1;
    }
}
