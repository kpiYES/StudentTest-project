package com.app.dao.mySQLImpl;

import com.app.dao.TestDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TestDAOImpl extends AbstractDAOImpl<Test> implements TestDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.test (test_id, subject_id, name) VALUES (NULL , ?, ?)";

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
    public Test findById(Long id) {
        return super.findById(id);
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
    Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        Set<Question> questions = new HashSet<>();
        if (resultSet.next()) {
            test.setId(resultSet.getLong("t.test_id"));
            test.setSubject(new Subject(resultSet.getLong("t.subject_id"), resultSet.getString("s.name")));
            test.setName(resultSet.getString("t.name"));
            questions.add(new Question(resultSet.getLong("q.question_id"), new Subject(resultSet.getLong("s_subject"),resultSet.getString("s.name")), resultSet.getString("query"), resultSet.getString("answer_1"), resultSet.getString("answer_2"), resultSet.getString("answer_3"), resultSet.getString("answer_4"), resultSet.getInt("correct_answer")));
        }
        test.setQuestionSet(questions);
        return test;
    }
}
