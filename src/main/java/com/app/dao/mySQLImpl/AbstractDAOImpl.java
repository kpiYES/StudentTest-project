package com.app.dao.mySQLImpl;

import com.app.dao.IAbstractDAO;
import com.app.util.ConnectionPool;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAOImpl<T extends Serializable> implements IAbstractDAO<T> {

    @Override
    public Long insert(T entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getInsertStatement(connection, entity);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getUpdateStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(T entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getDeleteStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getById(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getByIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    abstract PreparedStatement getUpdateStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getInsertStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getDeleteStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getByIdStatement(Connection connection, Long entity_id) throws SQLException;

    abstract T extractFromResultSet(ResultSet resultSet) throws SQLException;
}
