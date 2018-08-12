package com.app.dao.mySQLImpl;

import com.app.dao.IPassedTestDAO;
import com.app.model.PassedTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassedTestDAOImpl extends AbstractDAOImpl<PassedTest> implements IPassedTestDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.passed_test (passed_test_id, user_id, test_id, mark) VALUES (NULL, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.passed_test SET  user_id = ?, test_id = ?, mark = ? WHERE passed_test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.passed_test WHERE passed_test_id = ? AND user_id = ? AND test_id = ? AND mark = ?";

//    private static final String GET_BY_ID_QUERY =

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

//    @Override
//    public PassedTest getById(Long id) {
//        return super.getById(id);
//    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, PassedTest passedTest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setLong(1, passedTest.getUser().getId());
        preparedStatement.setLong(2, passedTest.getTest().getId());

        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, PassedTest passedTest) throws SQLException {
        return null;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, PassedTest passedTest) throws SQLException {
        return null;
    }

//    @Override
//    PreparedStatement getByIdStatement(Connection connection, Long entity_id) throws SQLException {
//        return null;
//    }

    @Override
    PassedTest extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
