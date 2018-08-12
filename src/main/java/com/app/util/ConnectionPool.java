package com.app.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/studentTest");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException();
        }
        return c;
    }

    private static class ConnectionPoolHolder {
        private final static ConnectionPool instance = new ConnectionPool();
    }
}
