// HX: Verbatim copy of assigns/10/MySolution/MyPQueue.java so that
//     Final_05 can satisfy the spec ("use MyPQueueArray.java implemented
//     in Assignment#9") without depending on a foreign source path.

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyPQueue<T> {
//
    int size();
//
    boolean isFull();
    boolean isEmpty();
//
    T top$raw();
    T top$opt();
    T top$exn() throws MyPQueueEmptyExn;
//
    T deque$raw();
    T deque$opt();
    T deque$exn() throws MyPQueueEmptyExn;
//
    void enque$raw(T itm);
    void enque$exn(T itm) throws MyPQueueFullExn;
    boolean enque$opt(T itm);
//
}
