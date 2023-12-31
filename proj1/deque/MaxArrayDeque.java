package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T item = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(item, get(i)) < 0) {
                item = get(i);
            }
        }

        return item;
    }
}
