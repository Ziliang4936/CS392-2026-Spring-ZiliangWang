package MyLibrary.MyMap;

import java.util.function.BiConsumer;

public class MyMapChain<V> implements MyMap<String, V> {
//
    private static class Entry<V> {
	String key; V val; Entry<V> next;
	Entry(String k, V v, Entry<V> n) { key = k; val = v; next = n; }
    }
//
    private Entry<V>[] table;
    private int capacity;
    private int size;
//
    @SuppressWarnings("unchecked")
    public MyMapChain(int cap) {
	this.capacity = cap;
	this.size = 0;
	this.table = new Entry[cap];
    }
//
    private int hash(String key) {
	return (key.hashCode() & 0x7fffffff) % capacity;
    }
//
    public int size() { return size; }
    public boolean isFull() { return false; }
    public boolean isEmpty() { return size == 0; }
//
    private Entry<V> find(int idx, String key) {
	Entry<V> e = table[idx];
	while (e != null) {
	    if (e.key.equals(key)) return e;
	    e = e.next;
	}
	return null;
    }
//
    public V search$exn(String key) {
	Entry<V> e = find(hash(key), key);
	if (e == null) throw new MyMapNoKeyExn();
	return e.val;
    }
    public V search$opt(String key) {
	Entry<V> e = find(hash(key), key);
	return (e == null) ? null : e.val;
    }
//
    public V insert$opt(String key, V val) {
	int idx = hash(key);
	Entry<V> e = find(idx, key);
	if (e != null) {
	    V old = e.val;
	    e.val = val;
	    return old;
	}
	table[idx] = new Entry<>(key, val, table[idx]);
	size++;
	return null;
    }
    public void insert$new(String key, V val) {
	int idx = hash(key);
	table[idx] = new Entry<>(key, val, table[idx]);
	size++;
    }
//
    public V remove$exn(String key) {
	int idx = hash(key);
	Entry<V> prev = null, e = table[idx];
	while (e != null) {
	    if (e.key.equals(key)) {
		V old = e.val;
		if (prev == null) table[idx] = e.next;
		else prev.next = e.next;
		size--;
		return old;
	    }
	    prev = e; e = e.next;
	}
	throw new MyMapNoKeyExn();
    }
    public V remove$opt(String key) {
	int idx = hash(key);
	Entry<V> prev = null, e = table[idx];
	while (e != null) {
	    if (e.key.equals(key)) {
		V old = e.val;
		if (prev == null) table[idx] = e.next;
		else prev.next = e.next;
		size--;
		return old;
	    }
	    prev = e; e = e.next;
	}
	return null;
    }
//
    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    Entry<V> e = table[i];
	    while (e != null) {
		work.accept(e.key, e.val);
		e = e.next;
	    }
	}
    }
//
} // end of [public class MyMapChain<V>{...}]
