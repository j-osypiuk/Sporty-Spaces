package org.example.repository;

import com.mongodb.client.FindIterable;

import java.util.List;

public interface Repository<T> extends AutoCloseable{

    boolean add(T obj);
    boolean remove(int id);
    T find(int id);
    boolean update(T obj);
    List<T> findAll();
}
