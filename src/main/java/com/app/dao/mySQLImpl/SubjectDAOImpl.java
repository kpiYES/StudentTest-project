package com.app.dao.mySQLImpl;

import com.app.dao.ISubjectDAO;
import com.app.model.Subject;
import com.app.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAOImpl implements ISubjectDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.subject (subject_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.subject SET name = ? WHERE subject_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.subject WHERE subject_id = ? AND name = ?";

    private static final String GET_BY_ID_QUERY = "SELECT subject_id, name FROM studenttest_app.subject WHERE subject_id = ?";

    private static final String GET_BY_NAME_QUERY = "SELECT subject_id, name FROM studenttest_app.subject WHERE name = ?";

    @Override
    public Long insert(Subject subject) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getInsertStatement(connection, subject);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Subject subject) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getUpdateStatement(connection, subject)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Subject subject) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getDeleteStatement(connection, subject)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Subject getById(Long subject_id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getByIdStatement(connection, subject_id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSubjectFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Subject getByName(String name) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getByNameStatement(connection, name);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSubjectFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement getInsertStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setString(1, subject.getName());
        return preparedStatement;
    }

    private PreparedStatement getUpdateStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setLong(2, subject.getId());
        return preparedStatement;
    }

    private PreparedStatement getDeleteStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, subject.getId());
        preparedStatement.setString(2, subject.getName());
        return preparedStatement;
    }

    private PreparedStatement getByIdStatement(Connection connection, Long subject_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
        preparedStatement.setLong(1, subject_id);
        return preparedStatement;
    }

    private Subject extractSubjectFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        if (resultSet.next()) {
            subject.setId(resultSet.getLong("subject_id"));
            subject.setName(resultSet.getString("name"));
        }
        return subject;
    }

    private PreparedStatement getByNameStatement(Connection connection, String name) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_QUERY);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }
}
