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
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newArr, 0, size);
        items = newArr;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst--] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        items[nextLast++] = item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = nextFirst + 1;
        System.out.print("[");
        for (int i = start; i < size - 1; i++) {
            System.out.print(items[i % items.length] + ", ");
        }
        System.out.println(items[(size - 1) % items.length] + "]");
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
