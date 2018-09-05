package com.app.dao.connection;

import com.app.exceptions.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSource {


    private static final String JNDI_DATA_SOURCE = "java:comp/env/jdbc/studentTest";
    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();
    private static DataSource dataSource;

    static {
        try {
            Context ctx = new InitialContext();
            dataSource = (javax.sql.DataSource) ctx.lookup(JNDI_DATA_SOURCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void bindConnection() {
        try {
            if (threadLocalConnection.get() == null)
                threadLocalConnection.set(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ConnectionException("Couldn't bind connection", e);
        }
    }

    public static Connection getConnection() {
        return threadLocalConnection.get();
    }

    public static void unbindConnection() {
        try {
            if (threadLocalConnection.get() != null)
                threadLocalConnection.get().close();
            threadLocalConnection.remove();
        } catch (SQLException e) {
            throw new ConnectionException("Couldn't unbind connection", e);
        }
    }
}
