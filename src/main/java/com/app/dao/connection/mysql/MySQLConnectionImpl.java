package com.app.dao.connection.mysql;

import com.app.dao.connection.DAOConnection;
import com.app.exceptions.InteractionDBException;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionImpl implements DAOConnection {

    private final Connection connection;
    private boolean isTransactionActive;

    public MySQLConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
            isTransactionActive = true;
        } catch (SQLException e) {
            throw new InteractionDBException("sdfgg", e);
        }
    }

    @Override
    public void startSerializableTransaction() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            isTransactionActive = true;
        } catch (SQLException e) {
            throw new InteractionDBException("jhfjke", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            isTransactionActive = false;
        } catch (SQLException e) {
            throw new InteractionDBException("jhfjf", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            isTransactionActive = false;
        } catch (SQLException e) {
            throw new InteractionDBException("dsfsdf", e);
        }
    }

    @Override
    public void close() {
        if (isTransactionActive) {
            rollback();
        }
        try {
            if (connection != null) {
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                connection.close();
            }
        } catch (SQLException e) {
            throw new InteractionDBException("sdfsdf", e);
        }
    }

    @Override
    public Object getNativeConnection() {
        return connection;
    }
}
