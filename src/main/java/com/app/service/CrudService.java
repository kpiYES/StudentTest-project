package com.app.service;

import java.util.Set;

public interface CrudService<T> {

    Long insert(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(Long id);

    Set<T> findAll();
}
