import Library00.FnList.*;
import Library00.LnList.*;
import Library00.LnStrm.*;
import Library00.FnTuple.*;
import Library00.MyMap00.*;

import java.util.function.BiConsumer;

public class Assign08_01<V>
    implements MyMap00<String, V> {

    private LnList<FnTupl2<String, V>> table[];
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public Assign08_01(int capacity) {
	this.capacity = capacity;
	this.size = 0;
	this.table = new LnList[capacity];
	for (int i = 0; i < capacity; i++) {
	    table[i] = new LnList<>();
	}
    }

    private int hash(String key) {
	return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private FnTupl2<String, V> findInChain(int idx, String key) {
	final FnTupl2<String, V>[] result = new FnTupl2[1];
	result[0] = null;
	table[idx].foritm1((pair) -> {
	    if (result[0] == null && pair.sub0.equals(key)) {
		result[0] = pair;
	    }
	});
	return result[0];
    }

    @SuppressWarnings("unchecked")
    private void doRemove(int idx, String key) {
	final LnList<FnTupl2<String, V>>[] nc = new LnList[1];
	nc[0] = new LnList<>();
	table[idx].foritm1((pair) -> {
	    if (!pair.sub0.equals(key)) {
		nc[0] = new LnList<>(pair, nc[0]);
	    }
	});
	table[idx] = nc[0];
	size--;
    }

    public int size() { return size; }
    public boolean isFull() { return false; }
    public boolean isEmpty() { return size == 0; }

    public V search$old(String key) {
	return findInChain(hash(key), key).sub1;
    }

    public V search$exn(String key) {
	FnTupl2<String, V> found = findInChain(hash(key), key);
	if (found == null) throw new MyMap00NoKeyExn();
	return found.sub1;
    }

    public V search$opt(String key) {
	FnTupl2<String, V> found = findInChain(hash(key), key);
	return (found == null) ? null : found.sub1;
    }

    public V insert$opt(String key, V val) {
	int idx = hash(key);
	FnTupl2<String, V> found = findInChain(idx, key);
	if (found != null) {
	    V old = found.sub1;
	    found.sub1 = val;
	    return old;
	}
	table[idx] = new LnList<>(new FnTupl2<>(key, val), table[idx]);
	size++;
	return null;
    }

    public void insert$new(String key, V val) {
	int idx = hash(key);
	table[idx] = new LnList<>(new FnTupl2<>(key, val), table[idx]);
	size++;
    }

    public V remove$old(String key) {
	int idx = hash(key);
	V old = findInChain(idx, key).sub1;
	doRemove(idx, key);
	return old;
    }

    public V remove$exn(String key) {
	int idx = hash(key);
	FnTupl2<String, V> found = findInChain(idx, key);
	if (found == null) throw new MyMap00NoKeyExn();
	V old = found.sub1;
	doRemove(idx, key);
	return old;
    }

    public V remove$opt(String key) {
	int idx = hash(key);
	FnTupl2<String, V> found = findInChain(idx, key);
	if (found == null) return null;
	V old = found.sub1;
	doRemove(idx, key);
	return old;
    }

    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
	return strmFromBucket(0);
    }

    private LnStrm<FnTupl2<String, V>> strmFromBucket(int idx) {
	return new LnStrm<>(() -> {
	    for (int i = idx; i < capacity; i++) {
		if (table[i].consq1()) {
		    return chainToStcn(table[i], i);
		}
	    }
	    return new LnStcn<>();
	});
    }

    private LnStcn<FnTupl2<String, V>> chainToStcn(
	LnList<FnTupl2<String, V>> chain, int bucketIdx) {
	FnTupl2<String, V> hd = chain.hd1();
	LnList<FnTupl2<String, V>> tl = chain.tl1();
	if (tl.consq1()) {
	    return new LnStcn<>(hd, new LnStrm<>(() -> chainToStcn(tl, bucketIdx)));
	} else {
	    return new LnStcn<>(hd, strmFromBucket(bucketIdx + 1));
	}
    }

    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    table[i].foritm1((pair) -> {
		work.accept(pair.sub0, pair.sub1);
	    });
	}
    }

    public static void main(String[] args) {
	Assign08_01<Integer> map = new Assign08_01<>(7);

	System.out.println("=== Insert ===");
	map.insert$new("apple", 1);
	map.insert$new("banana", 2);
	map.insert$new("cherry", 3);
	map.insert$new("date", 4);
	map.insert$new("elderberry", 5);
	System.out.println("size: " + map.size());

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

	System.out.println("\n=== Iterate (foritm) ===");
	map.foritm((k, v) -> System.out.println(k + " -> " + v));

	System.out.println("\n=== Stream (keyval_strmize) ===");
	LnStrm<FnTupl2<String, Integer>> strm = map.keyval_strmize();
	strm.foritm0((p) -> System.out.println(p.toString()));

	System.out.println("\n=== Exception test ===");
	try {
	    map.search$exn("nothere");
	} catch (MyMap00NoKeyExn e) {
	    System.out.println("MyMap00NoKeyExn caught for 'nothere'");
	}
    }
} // end of [public class Assign08_01{...}]
