package com.app.dao.mySQLImpl;

import com.app.dao.PassedTestDAO;
import com.app.dao.connection.ConnectionSource;
import com.app.exceptions.InteractionDBException;
import com.app.model.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class PassedTestDAOMySQLImpl extends AbstractDAOMySQLImpl<PassedTest> implements PassedTestDAO {

    private static final Logger logger = Logger.getLogger(PassedTestDAOMySQLImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.passed_test (passed_test_id, user_id, test_id, mark) VALUES (NULL, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.passed_test SET user_id = ?, test_id = ?, mark = ? WHERE passed_test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.passed_test WHERE passed_test_id = ? AND user_id = ? AND test_id = ? AND mark = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT pt.passed_test_id, pt.user_id, u.role_id, u.first_name, u.last_name, u.mail, u.salt, u.hash, r.name,  pt.test_id, t.subject_id, s.name, t.name, pt.mark FROM studenttest_app.passed_test pt INNER JOIN studenttest_app.user u ON pt.user_id = u.user_id INNER JOIN studenttest_app.role r ON u.role_id = r.role_id INNER JOIN studenttest_app.test t ON pt.test_id = t.test_id INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id WHERE pt.passed_test_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT pt.passed_test_id, pt.user_id, u.role_id, u.first_name, u.last_name, u.mail, u.salt, u.hash, r.name,  pt.test_id, t.subject_id, s.name, t.name, pt.mark FROM studenttest_app.passed_test pt INNER JOIN studenttest_app.user u ON pt.user_id = u.user_id INNER JOIN studenttest_app.role r ON u.role_id = r.role_id INNER JOIN studenttest_app.test t ON pt.test_id = t.test_id INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id";

    private static final String FIND_BY_USER_ID_AND_SUBJECT_ID_QUERY = "SELECT pt.passed_test_id, pt.user_id, u.role_id, u.first_name, u.last_name, u.mail, u.salt, u.hash, r.name,  pt.test_id, t.subject_id, s.name, t.name, pt.mark FROM studenttest_app.passed_test pt INNER JOIN studenttest_app.user u ON pt.user_id = u.user_id INNER JOIN studenttest_app.role r ON u.role_id = r.role_id INNER JOIN studenttest_app.test t ON pt.test_id = t.test_id INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id WHERE pt.user_id = ? AND t.subject_id = ?";

    private PassedTestDAOMySQLImpl() {
    }

    public static PassedTestDAOMySQLImpl getInstance() {
        return PassedTestDAOMySQLImplHolder.INSTANCE;
    }

    @Override
    public Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId) {
        Connection connection = ConnectionSource.getConnection();
        try (PreparedStatement preparedStatement = getFindByUserIdAndSubjectIdStatement(connection, userId, subjectId);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't find PassedTest by User Id and Subject Id ");
            throw new InteractionDBException("Couldn't find PassedTest by User Id and Subject Id", e);
        }
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, passedTest.getUser().getId());
        preparedStatement.setLong(2, passedTest.getTest().getId());
        preparedStatement.setInt(3, passedTest.getMark());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, passedTest.getUser().getId());
        preparedStatement.setLong(2, passedTest.getTest().getId());
        preparedStatement.setInt(3, passedTest.getMark());
        preparedStatement.setLong(4, passedTest.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, passedTest.getId());
        preparedStatement.setLong(2, passedTest.getUser().getId());
        preparedStatement.setLong(3, passedTest.getTest().getId());
        preparedStatement.setInt(4, passedTest.getMark());
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

    private PreparedStatement getFindByUserIdAndSubjectIdStatement(Connection connection, Long userId, Long subjectId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_AND_SUBJECT_ID_QUERY);
        preparedStatement.setLong(1, userId);
        preparedStatement.setLong(2, subjectId);
        return preparedStatement;
    }

    @Override
    protected PassedTest extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PassedTest passedTest = new PassedTest();
        if (resultSet.next()) {
            passedTest.setId(resultSet.getLong("pt.passed_test_id"));
            passedTest.setUser(new User(resultSet.getLong("pt.user_id"), new Role(resultSet.getLong("u.role_id"),
                    resultSet.getString("r.name")), resultSet.getString("u.first_name"),
                    resultSet.getString("u.last_name"), resultSet.getString("u.mail"),
                    resultSet.getString("u.salt"), resultSet.getString("u.hash")));
            passedTest.setTest(new Test(resultSet.getLong("pt.test_id"), new Subject(resultSet.getLong("t.subject_id"),
                    resultSet.getString("s.name")), resultSet.getString("t.name")));
            passedTest.setMark(resultSet.getInt("pt.mark"));
        }
        return passedTest;
    }

    private static class PassedTestDAOMySQLImplHolder {
        private final static PassedTestDAOMySQLImpl INSTANCE = new PassedTestDAOMySQLImpl();
    }
}
