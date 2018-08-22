package com.app.dao.mySQLImpl;

import com.app.dao.SubjectDAO;
import com.app.model.Subject;
import com.app.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class SubjectDAOImpl extends AbstractDAOImpl<Subject> implements SubjectDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.subject (subject_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.subject SET name = ? WHERE subject_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.subject WHERE subject_id = ? AND name = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT subject_id, name FROM studenttest_app.subject WHERE subject_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT subject_id, name FROM studenttest_app.subject";

    private static final String FIND_BY_NAME_QUERY = "SELECT subject_id, name FROM studenttest_app.subject WHERE name = ?";

    @Override
    public Long insert(Subject subject) {
        return super.insert(subject);
    }

    @Override
    public void update(Subject subject) {
        super.update(subject);
    }

    @Override
    public void delete(Subject subject) {
        super.delete(subject);
    }

    @Override
    public Subject findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Subject> findAll() {
        return super.findAll();
    }


    @Override()
    public Subject findByName(String name) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getFindByNameStatement(connection, name);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, subject.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setLong(2, subject.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, subject.getId());
        preparedStatement.setString(2, subject.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindByIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_QUERY);
    }

    private PreparedStatement getFindByNameStatement(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @Override
    Subject extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        if (resultSet.next()) {
            subject.setId(resultSet.getLong("subject_id"));
            subject.setName(resultSet.getString("name"));
        }
        return subject;
    }

    @Override
    Set<Subject> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return super.extractSetOfEntityFromResultSet(resultSet);
    }
}
