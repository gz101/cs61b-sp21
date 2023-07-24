package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    public static class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    public static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    public static class ReverseStringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return -a.compareTo(b);
        }
    }

    public static class ReverseIntegerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return -a.compareTo(b);
        }
    }


    @Test
    public void testMaxWithIntegerComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntegerComparator());

        assertNull(deque.max());

        deque.addLast(3);
        deque.addLast(5);
        deque.addLast(2);
        deque.addLast(8);

        assertEquals(8, (int) deque.max());
    }

    @Test
    public void testMinWithIntegerComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new ReverseIntegerComparator());

        assertNull(deque.max());

        deque.addLast(3);
        deque.addLast(5);
        deque.addLast(2);
        deque.addLast(8);

        assertEquals(2, (int) deque.max());
    }

    @Test
    public void testMaxReverseWithStringComparator() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new ReverseStringComparator());

        assertNull(deque.max());

        deque.addLast("hello");
        deque.addLast("world");
        deque.addLast("open");
        deque.addLast("ai");

        assertEquals("ai", deque.max());
    }

    @Test
    public void testMaxWithStringComparator() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringComparator());

        assertNull(deque.max());

        deque.addLast("hello");
        deque.addLast("world");
        deque.addLast("open");
        deque.addLast("ai");

        assertEquals("world", deque.max());
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);

        MaxArrayDeque<Person> deque = new MaxArrayDeque<>(ageComparator);

        assertNull(deque.max());

        deque.addLast(new Person("Alice", 25));
        deque.addLast(new Person("Bob", 30));
        deque.addLast(new Person("Charlie", 20));
        deque.addLast(new Person("David", 28));

        assertEquals(30, deque.max().getAge());
    }

    @Test
    public void testMaxWithEmptyDeque() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntegerComparator());

        assertNull(deque.max());
    }

    @Test
    public void testMaxWithSingleElement() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntegerComparator());

        deque.addLast(5);

        assertEquals(5, (int) deque.max());
    }

    @Test
    public void testEquals() {
        MaxArrayDeque<Integer> deque1 = new MaxArrayDeque<>(new IntegerComparator());
        MaxArrayDeque<Integer> deque2 = new MaxArrayDeque<>(new IntegerComparator());
        MaxArrayDeque<Integer> deque3 = new MaxArrayDeque<>(new IntegerComparator());

        deque1.addLast(1);
        deque1.addLast(3);
        deque1.addLast(2);

        deque2.addLast(1);
        deque2.addLast(3);
        deque2.addLast(2);

        deque3.addLast(3);
        deque3.addLast(2);
        deque3.addLast(1);

        assertTrue(deque1.equals(deque2));
        assertFalse(deque1.equals(deque3));
    }

    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }
}
