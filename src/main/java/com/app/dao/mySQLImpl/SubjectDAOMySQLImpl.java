package com.app.dao.mySQLImpl;

import com.app.dao.SubjectDAO;
import com.app.model.Subject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAOMySQLImpl extends AbstractDAOMySQLImpl<Subject> implements SubjectDAO {

    private static final Logger logger = Logger.getLogger(SubjectDAOMySQLImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.subject (subject_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.subject SET name = ? WHERE subject_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.subject WHERE subject_id = ? AND name = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT subject_id, name FROM studenttest_app.subject WHERE subject_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT subject_id, name FROM studenttest_app.subject";

    private SubjectDAOMySQLImpl() {
    }

    public static SubjectDAOMySQLImpl getInstance() {
        return SubjectDAOMySQLImpl.SubjectDAOMySQLImplHolder.INSTANCE;
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, subject.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setLong(2, subject.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(Connection connection, Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, subject.getId());
        preparedStatement.setString(2, subject.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getFindByIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_QUERY);
    }

    @Override
    protected Subject extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        if (resultSet.next()) {
            subject.setId(resultSet.getLong("subject_id"));
            subject.setName(resultSet.getString("name"));
        }
        return subject;
    }

    private static class SubjectDAOMySQLImplHolder {
        private final static SubjectDAOMySQLImpl INSTANCE = new SubjectDAOMySQLImpl();
    }
}
