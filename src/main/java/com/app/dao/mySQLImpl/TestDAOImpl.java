package com.app.dao.mySQLImpl;

import com.app.dao.TestDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TestDAOImpl extends AbstractDAOImpl<Test> implements TestDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.test (test_id, subject_id, name) VALUES (NULL , ?, ?)";

    private static final String CONNECT_TEST_AND_QUESTIONS_QUERY = "INSERT INTO studenttest_app.test_question  (test_id, question_id) VALUES (?,?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.test SET subject_id = ?, name = ? WHERE test_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.test WHERE test_id = ? AND subject_id = ? AND name = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name,q.question_id, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer" +
            " FROM studenttest_app.test t" +
            " INNER JOIN studenttest_app.subject s" +
            " ON t.subject_id = s.subject_id" +
            " INNER JOIN studenttest_app.test_question tq" +
            " ON t.test_id = tq.test_id" +
            " INNER JOIN studenttest_app.question q" +
            " ON tq.question_id = q.question_id" +
            " WHERE t.test_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name,q.question_id, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer" +
            " FROM studenttest_app.test t" +
            " INNER JOIN studenttest_app.subject s" +
            " ON t.subject_id = s.subject_id" +
            " INNER JOIN studenttest_app.test_question tq" +
            " ON t.test_id = tq.test_id" +
            " INNER JOIN studenttest_app.question q" +
            " ON tq.question_id = q.question_id";

    private static final String FIND_ALL_BY_SUBJECT_ID_QUERY = "SELECT t.test_id, t.subject_id, s.name, t.name" +
            " FROM studenttest_app.test t" +
            " INNER JOIN studenttest_app.subject s" +
            " ON t.subject_id = s.subject_id" +
            " WHERE s.subject_id = ?";


    @Override
    public Long insert(Test test) {
        System.out.println("inserttest");
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
    public Test findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Test> findAll() {
        return super.findAll();
    }

    @Override
    public Set<Test> findAllBySubjectId(Long id) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getFindAllBySubjectIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get user by mail", e);
        }
    }

    public void connectTestAndQuestions(Test test) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = getConnectTestAndQuestionsStatement(connection, test)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("dfsfdf", e);
        }
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

    @Override
    PreparedStatement getInsertStatement(Connection connection, Test test) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
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
        }
        return test;
    }

    @Override
    Set<Test> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return super.extractSetOfEntityFromResultSet(resultSet);
    }
}
