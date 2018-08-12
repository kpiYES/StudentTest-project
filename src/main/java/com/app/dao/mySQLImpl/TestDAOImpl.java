package com.app.dao.mySQLImpl;

import com.app.dao.ITestDAO;
import com.app.model.Subject;
import com.app.model.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDAOImpl extends AbstractDAOImpl<Test> implements ITestDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.test (test_id, subject_id, name) VALUES (NULL , ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.test SET subject_id = ?, name = ? WHERE test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.test WHERE test_id = ? AND subject_id = ? AND name = ?";

    private static final String GET_BY_ID_QUERY = "SELECT t.test_id, s.subject_id, s.name, t.name\n" +
            "FROM studenttest_app.test t INNER JOIN studenttest_app.subject s\n" +
            "ON t.subject_id = s.subject_id\n" +
            "WHERE t.test_id = ?";

    @Override
    public Long insert(Test test) {
        return super.insert(test);
    }

    @Override
    public void update(Test test) {
        super.update(test);
    }

    @Override
    public void delete(Test test) {
        super.delete(test);
    }

    @Override
    public Test getById(Long id) {
        return super.getById(id);
    }


    @Override
    PreparedStatement getInsertStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setLong(1, test.getSubject().getId());
        preparedStatement.setString(2, test.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, test.getSubject().getId());
        preparedStatement.setString(2, test.getName());
        preparedStatement.setLong(3, test.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, test.getId());
        preparedStatement.setLong(2, test.getSubject().getId());
        preparedStatement.setString(3, test.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getByIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    @Override
    Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        if (resultSet.next()) {
            test.setId(resultSet.getLong("t.test_id"));
            test.setSubject(new Subject(resultSet.getLong("s.subject_id"), resultSet.getString("s.name")));
            test.setName(resultSet.getString("t.name"));
        }
        return test;
    }
}
