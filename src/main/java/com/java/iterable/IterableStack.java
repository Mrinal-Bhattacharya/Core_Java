//****************************************************************
package com.java.iterable;

import java.util.Iterator;

/**
 * @param <E> TODO - Place class description here
 */
public class IterableStack<E> implements Iterable<E> {
    /**
     * Comment for <code>CAPACITY</code>
     */
    public static final int CAPACITY = 1000;
    private E s[];
    private int N;

    public E pop() {
        final E item = this.s[--this.N];
        this.s[this.N] = null;
        return item;
    }

    public void push(final E item) {
        this.s[this.N++] = item;
    }

    public int size() {
        return this.N;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = IterableStack.this.N;

        @Override
        public boolean hasNext() {
            return this.i > 0;
        }

        @Override
        public E next() {
            return IterableStack.this.s[--this.i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
