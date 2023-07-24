package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

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

    public boolean equals(Object o) {
        if (o instanceof MaxArrayDeque mad) {
            if (mad.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!mad.get(i).equals(this.get(i))) {
                    return false;
                }
            }

            if (!mad.max().equals(max())) {
                return false;
            }

            return true;
        }

        // not an instance of MaxArrayDeque
        return false;
    }
}
