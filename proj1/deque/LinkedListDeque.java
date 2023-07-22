package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private class ListNode<T> {
        ListNode<T> prev;
        T item;
        ListNode<T> next;

        public ListNode(T value) {
            prev = this;
            next = this;
            item = value;
        }
    }

    private int size;
    ListNode<T> sentinel;

    /**
     * Creates an empty linked list deque (no arguments).
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode<>(null);
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public void addFirst(T item) {
        ListNode<T> newNode = new ListNode<>(item);

        if (sentinel.next != sentinel) {
            // place in between sentinel and sentinel's next
            newNode.next = sentinel.next;
            sentinel.next.prev = newNode;
        } else {
            // it is the last node so point back to sentinel
            newNode.next = sentinel;
            sentinel.prev = newNode;
        }

        newNode.prev = sentinel;
        sentinel.next = newNode;

        size++;
    }

    @Override
    public void addLast(T item) {
        ListNode<T> newNode = new ListNode<>(item);

        newNode.prev = sentinel.prev;
        newNode.next = sentinel;

        if (sentinel.prev != sentinel) {
            // there is a node that comes after sentinel already
            sentinel.prev.next = newNode;
        } else {
            // it is the first node so sentinel points to it
            sentinel.next = newNode;
        }

        sentinel.prev = newNode;

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

    }

    @Override
    public T removeFirst() {
        // throw error if 0 items

        T value = sentinel.next.item;

        if (size == 1) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
        }

        size--;
        return value;
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
    public Iterator iterator() {
        return null;
    }
}
