package com.app.dao.mySQLImpl;

import com.app.dao.AbstractDAO;
import com.app.exceptions.InteractionDBException;
import com.app.model.AbstractEntity;
import com.app.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDAOImpl<T extends AbstractEntity> implements AbstractDAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

    protected Connection connection;

    protected AbstractDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Long insert(T entity) {
        Assert.isNotNull(entity, "entity must not be null");
        try (PreparedStatement preparedStatement = getInsertStatement(connection, entity)) {
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    logger.debug("Inserted entity with id: {}", generatedId);
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't insert entity");
            throw new InteractionDBException("Couldn't insert entity", e);
        }
        return null;
    }

    @Override
    public void update(T entity) {
        Assert.isNotNull(entity, "entity must not be null");
        try (PreparedStatement preparedStatement = getUpdateStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't update entity");
            throw new InteractionDBException("Couldn't update entity", e);

        }
    }

    @Override
    public void delete(T entity) {
        Assert.isNotNull(entity, "entity must not be null");
        try (PreparedStatement preparedStatement = getDeleteStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't delete entity");
            throw new InteractionDBException("Couldn't delete entity", e);
        }
    }

    @Override
    public T findById(Long id) {
        Assert.isNotNull(id, "id must not be null");
        try (PreparedStatement preparedStatement = getFindByIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Failed to fetch entity by id: {}", id);
            throw new InteractionDBException("Couldn't find entity by Id", e);
        }
    }

    @Override
    public Set<T> findAll() {
        try (PreparedStatement preparedStatement = getFindAllStatement(connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't find entity set");
            throw new InteractionDBException("Couldn't find entity Set", e);
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
