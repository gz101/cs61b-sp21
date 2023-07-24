package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayDeque oad) {
            if (oad.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!oad.get(i).equals(this.get(i))) {
                    return false;
                }
            }

            return true;
        }

        // not an instance of ArrayDeque
        return false;
    }

    private int getModIndex(int i) {
        int index = i % items.length;
        if (index < 0) {
            index += items.length;
        }
        return index;
    }

    public int getCapacity() {
        return items.length;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];

        int j = nextFirst + 1;
        if (capacity > items.length) {
            // increase array size
            for (int i = 0; i < size; i++) {
                newArr[i] = items[getModIndex(j)];
                j++;
            }
        } else {
            // decrease array size
            for (int i = 0; i < size; i++) {
                newArr[i] = items[getModIndex(j)];
                j++;
            }
        }
        nextFirst = capacity - 1;
        nextLast = size;

        items = newArr;
    }

    @Override
    public void addFirst(T item) {
        if (size() == getCapacity()) {
            resize(size() * 2);
        }

        items[getModIndex(nextFirst)] = item;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size() == getCapacity()) {
            resize(size() * 2);
        }

        items[getModIndex(nextLast)] = item;
        nextLast++;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = nextFirst + 1;
        System.out.print("[");
        for (int i = start; i < start + size - 1; i++) {
            System.out.print(items[getModIndex(i)] + ", ");
        }
        System.out.println(items[getModIndex(start + size - 1)] + "]");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T val = items[getModIndex(nextFirst + 1)];
        nextFirst++;
        size--;

        // resize if array too small
        if (size <= items.length / 4 && items.length > 8) {
            resize(items.length / 2);
        }

        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T val = items[getModIndex(nextLast - 1)];
        nextLast--;
        size--;

        // resize if array to small
        if (size <= items.length / 4 && items.length > 8) {
            resize(items.length / 2);
        }

        return val;
    }

    @Override
    public T get(int index) {
        int start = getModIndex(nextFirst + 1);
        return items[getModIndex(start + index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public void printBaseDeque() {
        System.out.print("[");
        for (int i = 0; i < getCapacity() - 1; i++) {
            System.out.print(items[i] + ", ");
        }
        System.out.println(items[getCapacity() - 1] + "]");
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            if (hasNext()) {
                int index = getModIndex(nextFirst + pos + 1);
                T item = items[index];
                pos++;
                return item;
            }
            return null;
        }
    }
}
