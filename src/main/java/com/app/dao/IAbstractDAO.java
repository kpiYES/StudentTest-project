package com.app.dao;

public interface IAbstractDAO<T> {
    Long insert(T entity);

    void update(T t);

    void delete(T t);

    T getById(Long id);
}
