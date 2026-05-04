package MyLibrary.MyMap;

import java.util.function.BiConsumer;

public class MyMapOAddr<V> implements MyMap<String, V> {
//
    private String[] keys;
    private Object[] vals;
    private boolean[] deleted;
    private int capacity;
    private int size;
//
    public MyMapOAddr(int cap) {
	this.capacity = cap;
	this.size = 0;
	this.keys = new String[cap];
	this.vals = new Object[cap];
	this.deleted = new boolean[cap];
    }
//
    private int hash(String key) {
	return (key.hashCode() & 0x7fffffff) % capacity;
    }
    private int probe(int h, int i) {
	return (h + i * i) % capacity;
    }
//
    private int findSlot(String key) {
	int h = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (keys[idx] == null && !deleted[idx]) return -1;
	    if (keys[idx] != null && keys[idx].equals(key)) return idx;
	}
	return -1;
    }
//
    public int size() { return size; }
    public boolean isFull() { return size >= capacity; }
    public boolean isEmpty() { return size == 0; }
//
    @SuppressWarnings("unchecked")
    public V search$exn(String key) {
	int idx = findSlot(key);
	if (idx < 0) throw new MyMapNoKeyExn();
	return (V) vals[idx];
    }
    @SuppressWarnings("unchecked")
    public V search$opt(String key) {
	int idx = findSlot(key);
	return (idx < 0) ? null : (V) vals[idx];
    }
//
    @SuppressWarnings("unchecked")
    public V insert$opt(String key, V val) {
	int h = hash(key);
	int firstDel = -1;
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (keys[idx] == null && !deleted[idx]) {
		int ins = (firstDel >= 0) ? firstDel : idx;
		keys[ins] = key; vals[ins] = val; deleted[ins] = false;
		size++;
		return null;
	    }
	    if (keys[idx] == null && deleted[idx]) {
		if (firstDel < 0) firstDel = idx;
		continue;
	    }
	    if (keys[idx].equals(key)) {
		V old = (V) vals[idx];
		vals[idx] = val;
		return old;
	    }
	}
	if (firstDel >= 0) {
	    keys[firstDel] = key; vals[firstDel] = val; deleted[firstDel] = false;
	    size++;
	    return null;
	}
	throw new MyMapFullExn();
    }
    public void insert$new(String key, V val) {
	int h = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (keys[idx] == null) {
		keys[idx] = key; vals[idx] = val; deleted[idx] = false;
		size++;
		return;
	    }
	}
	throw new MyMapFullExn();
    }
//
    @SuppressWarnings("unchecked")
    public V remove$exn(String key) {
	int idx = findSlot(key);
	if (idx < 0) throw new MyMapNoKeyExn();
	V old = (V) vals[idx];
	keys[idx] = null; vals[idx] = null; deleted[idx] = true;
	size--;
	return old;
    }
    @SuppressWarnings("unchecked")
    public V remove$opt(String key) {
	int idx = findSlot(key);
	if (idx < 0) return null;
	V old = (V) vals[idx];
	keys[idx] = null; vals[idx] = null; deleted[idx] = true;
	size--;
	return old;
    }
//
    @SuppressWarnings("unchecked")
    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    if (keys[i] != null) {
		work.accept(keys[i], (V) vals[i]);
	    }
	}
    }
//
} // end of [public class MyMapOAddr<V>{...}]
