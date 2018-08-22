package com.app.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DataManager {
//    public DataSource dataSource = null;
//    public Connection connection = null;
//
//    public DataManager(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public Connection getConnection() {
//        if (connection == null) {
//            connection = dataSource.getConnection();
//        }
//        return connection;
//    }
//
//    public Connection getTAConnection() {
//        try {
//            getConnection().setAutoCommit(false);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }
//
//    public void commitTA() {
//        try {
//            connection.commit();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//                connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//
//    public void rollbackTA() {
//        try {
//            connection.rollback();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//                connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }


}
