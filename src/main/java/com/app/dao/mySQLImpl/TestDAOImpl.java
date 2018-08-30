package com.app.dao.mySQLImpl;

import com.app.dao.TestDAO;
import com.app.exceptions.InteractionDBException;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TestDAOImpl extends AbstractDAOImpl<Test> implements TestDAO {

    private static final Logger logger = Logger.getLogger(TestDAOImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.test (test_id, subject_id, name, time_limit) VALUES (NULL , ?, ?, ?)";

    private static final String CONNECT_TEST_AND_QUESTIONS_QUERY = "INSERT INTO studenttest_app.test_question  (test_id, question_id) VALUES (?,?)";

    private static final String DISCONNECT_TEST_AND_QUESTIONS_QUERY = "DELETE FROM studenttest_app.test_question WHERE test_id = ?";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.test SET subject_id = ?, name = ?, time_limit = ? WHERE test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.test WHERE test_id = ? AND subject_id = ? AND name = ? AND time_limit = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name, t.time_limit  FROM studenttest_app.test t INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id WHERE t.test_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name, t.time_limit FROM studenttest_app.test t INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id";

    private static final String FIND_ALL_BY_SUBJECT_ID_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name, t.time_limit FROM studenttest_app.test t INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id WHERE s.subject_id = ?";

    private static final String FIND_ALL_BY_QUESTION_ID_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name, t.time_limit FROM studenttest_app.test t INNER JOIN studenttest_app.subject s ON t.subject_id = s.subject_id INNER JOIN studenttest_app.test_question tq ON t.test_id = tq.test_id WHERE tq.question_id = ?";

    public TestDAOImpl(Connection connection) {
        super(connection);
    }


    @Override
    public Long insert(Test test){
        Long id = super.insert(test);
        test.setId(id);
        connectTestAndQuestions(test);
        return id;
    }

    @Override
    public void delete(Test test) {
        disconnectTestAndQuestions(test);
        super.delete(test);
    }

    @Override
    public Set<Test> findAllBySubjectId(Long id) {
        try (PreparedStatement preparedStatement = getFindAllBySubjectIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Couldn't find Test Set by Subject Id");
            throw new InteractionDBException("Couldn't find Test Set by Subject Id", e);
        }
    }

    @Override
    public Set<Test> findAllByQuestionId(Long id) {
        try (PreparedStatement preparedStatement = getFindAllByQuestionIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Couldn't find Test Set by Question Id");
            throw new InteractionDBException("Couldn't find Test Set by Question Id", e);
        }
    }


    private void connectTestAndQuestions(Test test) {
        try (PreparedStatement preparedStatement = getConnectTestAndQuestionsStatement(connection, test)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Couldn't connect Test and Questions");
            throw new InteractionDBException("Couldn't connect Test And Questions", e);
        }
    }


    private void disconnectTestAndQuestions(Test test) {
        try (PreparedStatement preparedStatement = getDisconnectTestAndQuestionsStatement(connection, test)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Couldn't disconnect Test and Questions");
            throw new InteractionDBException("Couldn't disconnect Test and Questions", e);
        }
    }

    public PreparedStatement getFindAllByQuestionIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_QUESTION_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    public PreparedStatement getConnectTestAndQuestionsStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CONNECT_TEST_AND_QUESTIONS_QUERY);
        for (Question question : test.getQuestionSet()) {
            preparedStatement.setLong(1, test.getId());
            preparedStatement.setLong(2, question.getId());
            preparedStatement.addBatch();
        }
        return preparedStatement;
    }

    public PreparedStatement getDisconnectTestAndQuestionsStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_TEST_AND_QUESTIONS_QUERY);
        preparedStatement.setLong(1, test.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, test.getSubject().getId());
        preparedStatement.setString(2, test.getName());
        preparedStatement.setInt(3, test.getTimeLimit());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, test.getSubject().getId());
        preparedStatement.setString(2, test.getName());
        preparedStatement.setInt(3, test.getTimeLimit());
        preparedStatement.setLong(4, test.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, test.getId());
        preparedStatement.setLong(2, test.getSubject().getId());
        preparedStatement.setString(3, test.getName());
        preparedStatement.setInt(4, test.getTimeLimit());
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

    private PreparedStatement getFindAllBySubjectIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_SUBJECT_ID_QUERY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    @Override
    Test extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        if (resultSet.next()) {
            test.setId(resultSet.getLong("t.test_id"));
            test.setSubject(new Subject(resultSet.getLong("t.subject_id"), resultSet.getString("s.name")));
            test.setName(resultSet.getString("t.name"));
            test.setTimeLimit(resultSet.getInt("t.time_limit"));
        }
        return test;
    }

    @Override
    Set<Test> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return super.extractSetOfEntityFromResultSet(resultSet);
    }
}
