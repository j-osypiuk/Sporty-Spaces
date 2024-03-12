package org.example.manager;

import org.example.repository.Repository;

import java.util.List;

public class Manager<T> implements AutoCloseable {

    private final Repository<T> repository;

    public Manager(Repository<T> repository) {
        this.repository = repository;
    }

    public boolean add(T obj) {
        return repository.add(obj);
    }
    public boolean update(T obj) {
        return repository.update(obj);
    }
    public T find(int id) {
        return repository.find(id);
    }
    public List<T> findAll() {
        return repository.findAll();
    }
    public boolean remove(int id) {
        return repository.remove(id);
    }

    @Override
    public void close() throws Exception {
        repository.close();
    }
}
