package MyLibrary.MyHeap;

import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;

public class MyHeap<T> {
//
    private Object[] data;
    private int size;
    private int capacity;
    private ToIntBiFunction<T, T> cmp;
//
    @SuppressWarnings("unchecked")
    public MyHeap(int cap, ToIntBiFunction<T, T> cmp) {
	this.capacity = cap;
	this.size = 0;
	this.data = new Object[cap];
	this.cmp = cmp;
    }
//
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size >= capacity; }
//
    @SuppressWarnings("unchecked")
    public T peek() {
	if (size == 0) return null;
	return (T) data[0];
    }
//
    @SuppressWarnings("unchecked")
    public void insert(T item) {
	if (size >= capacity) {
	    grow();
	}
	data[size] = item;
	siftUp(size);
	size++;
    }
//
    @SuppressWarnings("unchecked")
    public T extract() {
	if (size == 0) return null;
	T top = (T) data[0];
	size--;
	data[0] = data[size];
	data[size] = null;
	if (size > 0) siftDown(0);
	return top;
    }
//
    @SuppressWarnings("unchecked")
    private void siftUp(int i) {
	while (i > 0) {
	    int parent = (i - 1) / 2;
	    if (cmp.applyAsInt((T) data[i], (T) data[parent]) < 0) {
		swap(i, parent);
		i = parent;
	    } else {
		break;
	    }
	}
    }
//
    @SuppressWarnings("unchecked")
    private void siftDown(int i) {
	while (true) {
	    int left = 2 * i + 1;
	    int right = 2 * i + 2;
	    int smallest = i;
	    if (left < size &&
		cmp.applyAsInt((T) data[left], (T) data[smallest]) < 0)
		smallest = left;
	    if (right < size &&
		cmp.applyAsInt((T) data[right], (T) data[smallest]) < 0)
		smallest = right;
	    if (smallest == i) break;
	    swap(i, smallest);
	    i = smallest;
	}
    }
//
    private void swap(int i, int j) {
	Object tmp = data[i];
	data[i] = data[j];
	data[j] = tmp;
    }
//
    @SuppressWarnings("unchecked")
    private void grow() {
	int newCap = capacity * 2;
	Object[] newData = new Object[newCap];
	for (int i = 0; i < size; i++) {
	    newData[i] = data[i];
	}
	data = newData;
	capacity = newCap;
    }
//
    @SuppressWarnings("unchecked")
    public void foritm(Consumer<? super T> work) {
	for (int i = 0; i < size; i++) {
	    work.accept((T) data[i]);
	}
    }
//
} // end of [public class MyHeap<T>{...}]
