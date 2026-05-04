package Library00.MyMap00;

import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnTuple.*;

import java.util.function.BiConsumer;

/*
HX-2025-11-15:
HX-2026-04-01:
A map consists of DISTINCT keys mapping to values;
and each key is mapped to exactly one value.
*/

public interface MyMap00<K,V> {
//
    int size(); // the number of keys
//
    boolean isFull(); // checks for fullness // no room
    boolean isEmpty(); // checks for emptiness // no keys
//
    LnStrm<FnTupl2<K,V>> keyval_strmize();
//
    V search$old(K key); // HX: [key] is assumed to be in the map
    V search$exn(K key); // HX: exception if [key] is not in the map
    V search$opt(K key); // HX: return null if [key] is not in the map
//
    // HX: insert$opt returns the old V associated with the key if
    V insert$opt(K key, V val); // available, or returns the null value.
    void insert$new(K key, V val); // the key is assumed not in the map
//
    V remove$old(K key); // HX: [key] is assumed to be in the map
    V remove$exn(K key); // HX: exception if [key] is not in the map
    V remove$opt(K key); // HX: return null if [key] is not in the map
//
    void foritm(BiConsumer<? super K, ? super V> work);
//
} // end of [interface MyMap00<T>{...}]
