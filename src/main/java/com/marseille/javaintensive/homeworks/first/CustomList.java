package com.marseille.javaintensive.homeworks.first;

import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {

    int size();

    void add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    boolean remove(Object o);

    void sort(Comparator<? super E> c);


}
