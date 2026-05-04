package MyLibrary.FnGtree;

import MyLibrary.FnList.*;

public interface FnGtree<T> {
    T value();
    FnList<FnGtree<T>> children();
}
