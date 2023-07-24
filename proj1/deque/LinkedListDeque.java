package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private final ListNode<T> sentinel;
    private int size;

    /**
     * Creates an empty linked list deque (no arguments).
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode<>(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            LinkedListDeque<T> olld = (LinkedListDeque<T>) obj;

            if (olld.size() != this.size()) {
                return false;
            }

            Iterator<T> it1 = this.iterator();
            Iterator<T> it2 = olld.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                if (!it1.next().equals(it2.next())) {
                    return false;
                }
            }

            return true;
        }

        // not an instance of LinkedListDeque
        return false;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (isEmpty() || index >= size()) {
            return null;
        }

        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, ListNode<T> ptr) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(index - 1, ptr.next);
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
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ListNode<T> ptr = sentinel.next;
        while (ptr != sentinel) {
            if (ptr.next != sentinel) {
                System.out.print(ptr.item + " -> ");
            } else {
                System.out.println(ptr.item);
            }
            ptr = ptr.next;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

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
        if (size <= 1) {
            return removeFirst();
        }

        T value = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index >= size()) {
            return null;
        }

        ListNode<T> ptr = sentinel.next;
        while (index != 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

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

    private class LinkedListDequeIterator implements Iterator<T> {
        private ListNode<T> pos;

        public LinkedListDequeIterator() {
            pos = sentinel.next;
        }

        public boolean hasNext() {
            return pos != sentinel;
        }

        public T next() {
            T item = pos.item;
            pos = pos.next;
            return item;
        }
    }
}
