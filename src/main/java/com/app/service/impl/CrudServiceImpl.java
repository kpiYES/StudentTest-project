package com.app.service.impl;

import com.app.dao.AbstractDAO;
import com.app.dao.connection.DAOConnection;
import com.app.dao.factory.DAOFactory;
import com.app.model.AbstractEntity;
import com.app.service.CrudService;

import java.util.Set;

public abstract class CrudServiceImpl<T extends AbstractEntity> implements CrudService<T> {

    protected final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    @Override
    public Long insert(T entity) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            AbstractDAO<T> dao = getConcreteDAO(daoConnection);
            return dao.insert(entity);
        }
    }

    @Override
    public void update(T entity) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            AbstractDAO<T> dao = getConcreteDAO(daoConnection);
            dao.update(entity);
        }
    }

    @Override
    public void delete(T entity) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            AbstractDAO<T> dao = getConcreteDAO(daoConnection);
            dao.delete(entity);
        }
    }

    @Override
    public T findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            AbstractDAO<T> dao = getConcreteDAO(daoConnection);
            return dao.findById(id);
        }
    }

    @Override
    public Set<T> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            AbstractDAO<T> dao = getConcreteDAO(daoConnection);
            return dao.findAll();
        }
    }

    protected abstract AbstractDAO<T> getConcreteDAO(DAOConnection daoConnection);
}
