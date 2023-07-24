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

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            MaxArrayDeque<T> mad = (MaxArrayDeque<T>) obj;

            if (mad.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!mad.get(i).equals(this.get(i))) {
                    return false;
                }
            }

            return mad.max().equals(max());
        }

        // not an instance of MaxArrayDeque
        return false;
    }
}
