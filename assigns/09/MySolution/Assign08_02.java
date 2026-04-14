import Library00.FnList.*;
import Library00.LnList.*;
import Library00.LnStrm.*;
import Library00.FnTuple.*;
import Library00.MyMap00.*;

import java.util.function.BiConsumer;

public class Assign08_02<V>
    implements MyMap00<String, V> {

    private FnTupl2<String, V> table[];
    private int capacity;
    private int size;
    private final FnTupl2<String, V> DELETED;

    @SuppressWarnings("unchecked")
    public Assign08_02(int capacity) {
	this.capacity = capacity;
	this.size = 0;
	this.table = new FnTupl2[capacity];
	this.DELETED = new FnTupl2<>(null, null);
    }

    private int hash(String key) {
	return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private int probe(int h, int i) {
	return (h + i * i) % capacity;
    }

    private int findSlot(String key) {
	int h = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (table[idx] == null) return -1;
	    if (table[idx] != DELETED && table[idx].sub0.equals(key))
		return idx;
	}
	return -1;
    }

    public int size() { return size; }
    public boolean isFull() { return size >= capacity; }
    public boolean isEmpty() { return size == 0; }

    public V search$old(String key) {
	return table[findSlot(key)].sub1;
    }

    public V search$exn(String key) {
	int idx = findSlot(key);
	if (idx < 0) throw new MyMap00NoKeyExn();
	return table[idx].sub1;
    }

    public V search$opt(String key) {
	int idx = findSlot(key);
	return (idx < 0) ? null : table[idx].sub1;
    }

    public V insert$opt(String key, V val) {
	int h = hash(key);
	int firstDeleted = -1;
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (table[idx] == null) {
		int ins = (firstDeleted >= 0) ? firstDeleted : idx;
		table[ins] = new FnTupl2<>(key, val);
		size++;
		return null;
	    }
	    if (table[idx] == DELETED) {
		if (firstDeleted < 0) firstDeleted = idx;
		continue;
	    }
	    if (table[idx].sub0.equals(key)) {
		V old = table[idx].sub1;
		table[idx].sub1 = val;
		return old;
	    }
	}
	if (firstDeleted >= 0) {
	    table[firstDeleted] = new FnTupl2<>(key, val);
	    size++;
	    return null;
	}
	throw new MyMap00FullExn();
    }

    public void insert$new(String key, V val) {
	int h = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(h, i);
	    if (table[idx] == null || table[idx] == DELETED) {
		table[idx] = new FnTupl2<>(key, val);
		size++;
		return;
	    }
	}
	throw new MyMap00FullExn();
    }

    public V remove$old(String key) {
	int idx = findSlot(key);
	V old = table[idx].sub1;
	table[idx] = DELETED;
	size--;
	return old;
    }

    public V remove$exn(String key) {
	int idx = findSlot(key);
	if (idx < 0) throw new MyMap00NoKeyExn();
	V old = table[idx].sub1;
	table[idx] = DELETED;
	size--;
	return old;
    }

    public V remove$opt(String key) {
	int idx = findSlot(key);
	if (idx < 0) return null;
	V old = table[idx].sub1;
	table[idx] = DELETED;
	size--;
	return old;
    }

    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
	return strmFromIndex(0);
    }

    private LnStrm<FnTupl2<String, V>> strmFromIndex(int idx) {
	return new LnStrm<>(() -> {
	    for (int i = idx; i < capacity; i++) {
		if (table[i] != null && table[i] != DELETED) {
		    return new LnStcn<>(table[i], strmFromIndex(i + 1));
		}
	    }
	    return new LnStcn<>();
	});
    }

    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    if (table[i] != null && table[i] != DELETED) {
		work.accept(table[i].sub0, table[i].sub1);
	    }
	}
    }

    public static void main(String[] args) {
	Assign08_02<Integer> map = new Assign08_02<>(11);

	System.out.println("=== Insert ===");
	map.insert$new("apple", 1);
	map.insert$new("banana", 2);
	map.insert$new("cherry", 3);
	map.insert$new("date", 4);
	map.insert$new("elderberry", 5);
	System.out.println("size: " + map.size());
	System.out.println("isFull: " + map.isFull());

	System.out.println("\n=== Search ===");
	System.out.println("apple: " + map.search$opt("apple"));
	System.out.println("banana: " + map.search$opt("banana"));
	System.out.println("cherry: " + map.search$opt("cherry"));
	System.out.println("missing: " + map.search$opt("missing"));

	System.out.println("\n=== Update (insert$opt) ===");
	Integer old = map.insert$opt("banana", 20);
	System.out.println("old banana: " + old);
	System.out.println("new banana: " + map.search$opt("banana"));

	System.out.println("\n=== Remove ===");
	Integer removed = map.remove$opt("cherry");
	System.out.println("removed cherry: " + removed);
	System.out.println("cherry after remove: " + map.search$opt("cherry"));
	System.out.println("size after remove: " + map.size());

	System.out.println("\n=== Insert after remove (reuse tombstone) ===");
	map.insert$new("fig", 6);
	System.out.println("fig: " + map.search$opt("fig"));
	System.out.println("size: " + map.size());

	System.out.println("\n=== Iterate (foritm) ===");
	map.foritm((k, v) -> System.out.println(k + " -> " + v));

	System.out.println("\n=== Stream (keyval_strmize) ===");
	LnStrm<FnTupl2<String, Integer>> strm = map.keyval_strmize();
	strm.foritm0((p) -> System.out.println(p.toString()));

	System.out.println("\n=== Exception tests ===");
	try {
	    map.search$exn("nothere");
	} catch (MyMap00NoKeyExn e) {
	    System.out.println("MyMap00NoKeyExn caught for search 'nothere'");
	}
	try {
	    map.remove$exn("nothere");
	} catch (MyMap00NoKeyExn e) {
	    System.out.println("MyMap00NoKeyExn caught for remove 'nothere'");
	}
    }
} // end of [public class Assign08_02{...}]
