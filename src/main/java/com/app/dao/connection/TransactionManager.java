package com.app.dao.connection;

import com.app.exceptions.InteractionDBException;

import java.sql.Connection;
import java.sql.SQLException;



public class TransactionManager {


    public static void beginTransaction() {
        try {
            ConnectionSource.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new InteractionDBException("Couldn't begin transaction", e);
        }
    }

    public static void rollbackTransaction() {
        try {
            Connection connection = ConnectionSource.getConnection();
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new InteractionDBException("Couldn't rollback transaction", e);
        }
    }

    public static void commitTransaction() {
        try {
            Connection connection = ConnectionSource.getConnection();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new InteractionDBException("Couldn't commit transaction", e);
        }
    }
}

