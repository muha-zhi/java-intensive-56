package com.marseille.javaintensive.homeworks.first;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private Object[] elementData;

    private int size;


    public CustomArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        checkInitialCapacity(initialCapacity);
    }

    private void checkInitialCapacity(int initialCapacity) {

        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }

    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void add(int index, E element) {

        checkIndex(index);
        checkElementDataSizeAndChangeSize(1);
        addElementWithIndex(index, element);

    }

    private void addElementWithIndex(int index, E element) {

        if (elementData[index] != null) shiftElementsInElementData(
                index,
                index + 1,
                size - index);

        elementData[index] = element;

        size++;

    }

    private void checkElementDataSizeAndChangeSize(int sizeOfNewData) {

        if ((size + sizeOfNewData) >= elementData.length) changeElementDataSize(sizeOfNewData);

    }

    private void changeElementDataSize(int sizeOfNewData) {

        Object[] newElementData = new Object[(size + sizeOfNewData) * 2];

        System.arraycopy(elementData, 0, newElementData, 0, size);

        elementData = newElementData;

    }

    private void shiftElementsInElementData(int fromIndex,
                                            int toIndex,
                                            int countElementsToCopy) {

        System.arraycopy(elementData, fromIndex, elementData, toIndex, countElementsToCopy);

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        if (c == null || c.isEmpty()) return false;

        checkElementDataSizeAndChangeSize(c.size());

        addAllElementsToNewArray(c);

        return true;

    }

    private void addAllElementsToNewArray(Collection<? extends E> c) {

        for (E element : c) {
            elementData[size++] = element;
        }
    }

    @Override
    public void clear() {
        removeAllElements();
        size = 0;
    }

    private void removeAllElements() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {

        checkIndex(index);
        return (E) elementData[index];

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {

        checkIndex(index);

        E element = (E) elementData[index];

        elementData[index] = null;

        shiftElementsInElementData(index + 1, index, size - index - 1);

        elementData[size - 1] = null;

        size--;

        return element;
    }

    private void checkIndex(int index) {
        if (index > elementData.length - 1 || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + " ," + " Size: " + size);
    }

    @Override
    public boolean remove(Object o) {

        if (o == null) return false;

        boolean isRemoved = false;

        for (int i = 0; i < size; i++) {

            if (elementData[i].equals(o)) {

                elementData[i] = null;
                shiftElementsInElementData(i + 1, i, size - i - 1);

                elementData[size - 1] = null;

                isRemoved = true;

                size--;

                break;
            }
        }

        return isRemoved;

    }

    @Override
    public void sort(Comparator<? super E> c) {

        if (c == null) throw new NullPointerException();

        mergeSort(elementData, c);

    }

    public void mergeSort(Object[] elementData, Comparator<? super E> c) {
        if (elementData.length > 1) {
            int mid = elementData.length / 2;
            Object[] left = Arrays.copyOfRange(elementData, 0, mid);
            Object[] right = Arrays.copyOfRange(elementData, mid, elementData.length);

            mergeSort(left, c);
            mergeSort(right, c);

            merge(elementData, left, right, c);
        }
    }

    private void merge(Object[] result, Object[] left, Object[] right, Comparator<? super E> c) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] == null) {
                result[k++] = right[j++];
            } else if (right[j] == null) {
                result[k++] = left[i++];
            } else if (c.compare((E) left[i], (E) right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
