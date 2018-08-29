package com.app.dao.connection;

public interface DAOConnection extends AutoCloseable {

    void startTransaction();

    void startSerializableTransaction();


    void commit();


    void rollback();


    Object getNativeConnection();
// return type Connection?

    @Override
    void close();

}
