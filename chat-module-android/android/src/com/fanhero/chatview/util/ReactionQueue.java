package com.fanhero.chatview.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ReactionQueue<E> implements Queue<E> {

    private static final String TAG = "ReactionQueue";

    private List<E> listArray;

    public ReactionQueue() {
        listArray = new ArrayList<>();
    }

    public void enqueue(E anObject) {
        if (!isFull()) {
            listArray.add(anObject);
        } else {
            Log.i(TAG, "Queue is full, can't add anymore.");
        }
    }

    public E dequeue() {
        if (listArray.isEmpty()) {
            return null;
        }

        E headObject = listArray.get(0);
        if (headObject != null) {
            listArray.remove(0);
        }

        return headObject;
    }

    public boolean isFull() {
        return listArray.size() == 20;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
