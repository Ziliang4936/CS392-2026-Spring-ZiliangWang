package Library00.FnGtree;

import Library00.FnList.*;

public interface FnGtree<T> {
    T value();
    FnList<FnGtree<T>> children();
}
