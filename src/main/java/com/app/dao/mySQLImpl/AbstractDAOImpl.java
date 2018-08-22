package com.app.dao.mySQLImpl;

import com.app.dao.AbstractDAO;
import com.app.util.DataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDAOImpl<T extends Serializable> implements AbstractDAO<T> {

    @Override
    public Long insert(T entity) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getInsertStatement(connection, entity)) {
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(T entity) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getUpdateStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(T entity) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getDeleteStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findById(Long id) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getFindByIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<T> findAll() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getFindAllStatement(connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(111);
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    abstract PreparedStatement getUpdateStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getInsertStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getDeleteStatement(Connection connection, T entity) throws SQLException;

    abstract PreparedStatement getFindByIdStatement(Connection connection, Long entity_id) throws SQLException;

    abstract PreparedStatement getFindAllStatement(Connection connection) throws SQLException;

    abstract T extractEntityFromResultSet(ResultSet resultSet) throws SQLException;

    Set<T> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Set<T> entitySet = new HashSet<>();

        while (resultSet.next()) {
            resultSet.previous();
            entitySet.add(extractEntityFromResultSet(resultSet));
        }
        return entitySet;
    }
}
