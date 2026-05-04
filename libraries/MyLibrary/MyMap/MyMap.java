package MyLibrary.MyMap;

import java.util.function.BiConsumer;

public interface MyMap<K,V> {
//
    int size();
//
    boolean isFull();
    boolean isEmpty();
//
    V search$exn(K key) throws MyMapNoKeyExn;
    V search$opt(K key);
//
    V insert$opt(K key, V val);
    void insert$new(K key, V val);
//
    V remove$exn(K key) throws MyMapNoKeyExn;
    V remove$opt(K key);
//
    void foritm(BiConsumer<? super K, ? super V> work);
//
} // end of [interface MyMap<K,V>{...}]
