package MyLibrary.MyMap00;

import MyLibrary.FnList.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyMap00RBST<K extends Comparable<K>, V> implements MyMap00<K, V> {
    private Node root;

    private class Node {
	K key;
	V val;
	int size;
        Node lchild;
	Node rchild;
    }

}
