package com.app.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {


    private static final String JNDI_DATA_SOURCE = "java:comp/env/jdbc/studentTest";
    private static final String QUERY_PROPERTIES_LOCATION = "mySQL/queries.properties";

    private DataSource() {
    }

    public static DataSource getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    public Connection getConnection() {
        Context ctx;
        Connection c;
        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(JNDI_DATA_SOURCE);
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException();
        }
        return c;
    }

    private static class DataSourceHolder {
        private static final DataSource INSTANCE = new DataSource();
    }
}
