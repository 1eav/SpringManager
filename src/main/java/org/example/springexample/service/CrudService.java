package org.example.springexample.service;

import java.util.Collection;

public interface CrudService<T> {
    Collection<T> getAll();
    T getById(Long id);
    void create(T item);
    void update(T item);
    void delete(Long id);
}