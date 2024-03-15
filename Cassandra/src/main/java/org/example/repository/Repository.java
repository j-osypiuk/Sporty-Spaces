package org.example.repository;

public interface Repository<T> extends AutoCloseable{

    boolean add(T obj);
    boolean remove(int id);
    T find(int id);
    boolean update(T obj);
}
