package com.app.dao.mySQLImpl;

import com.app.dao.PassedTestDAO;
import com.app.model.PassedTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassedTestDAOImpl extends AbstractDAOImpl<PassedTest> implements PassedTestDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.passed_test (passed_test_id, user_id, test_id, mark) VALUES (NULL, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.passed_test SET user_id = ?, test_id = ?, mark = ? WHERE passed_test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.passed_test WHERE passed_test_id = ? AND user_id = ? AND test_id = ? AND mark = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT pt.passed_test_id, pt.user_id, u.role_id, u.first_name, u.last_name, u.mail, u.salt, u.hash, r.name,  pt.test_id, t.subject_id, s.name, t.name, pt.mark" +
            " FROM studenttest_app.passed_test pt" +
            " INNER JOIN studenttest_app.user u" +
            " ON pt.user_id = u.user_id" +
            " INNER JOIN studenttest_app.role r" +
            " ON u.role_id = r.role_id" +
            " INNER JOIN studenttest_app.test t" +
            " ON pt.test_id = t.test_id" +
            " INNER JOIN studenttest_app.subject s" +
            " ON t.subject_id = s.subject_id" +
            " WHERE pt.passed_test_id = ?";

    @Override
    public Long insert(PassedTest passedTest) {
        return super.insert(passedTest);
    }

    @Override
    public void update(PassedTest passedTest) {
        super.update(passedTest);
    }

    @Override
    public void delete(PassedTest passedTest) {
        super.delete(passedTest);
    }

    @Override
    public PassedTest findById(Long id) {
        return super.findById(id);
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, passedTest.getUser().getId());
        preparedStatement.setLong(2, passedTest.getTest().getId());
        preparedStatement.setInt(3, passedTest.getMark());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, passedTest.getUser().getId());
        preparedStatement.setLong(2, passedTest.getTest().getId());
        preparedStatement.setInt(3, passedTest.getMark());
        preparedStatement.setLong(4, passedTest.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, passedTest.getId());
        preparedStatement.setLong(2, passedTest.getUser().getId());
        preparedStatement.setLong(3, passedTest.getTest().getId());
        preparedStatement.setInt(4, passedTest.getMark());
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindByIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    @Override
    PassedTest extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
