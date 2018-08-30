package com.app.dao;

import com.app.model.AbstractEntity;

import java.util.Set;

public interface AbstractDAO<T extends AbstractEntity> {

    Long insert(T entity);

    void update(T t);

    void delete(T t);

    T findById(Long id);

    Set<T> findAll();
}
