package com.app.dao;

import java.util.Set;

public interface AbstractDAO<T> {

    /**
     * Insert object to a database.
     *
     * @param entity object to insert
     * @return id
     */
    Long insert(T entity);

    /**
     * Update object's information in database.
     *
     * @param t object to update
     */
    void update(T t);

    /**
     * Delete certain object, identified by id, from database.
     *
     * @param t identifier of the object.
     */
    void delete(T t);

    /**
     * Retrieves object data from database.
     *
     * @param id of object.
     * @return List of objects which represent one row in database.
     */
    T findById(Long id);

    /**
     * Retrieves all object data from database.
     *
     * @return List of objects which represent one row in database.
     */
    Set<T> findAll();
}
