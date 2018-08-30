package com.app.dao.mySQLImpl;

import com.app.dao.PassedQuestionDAO;
import com.app.exceptions.InteractionDBException;
import com.app.model.PassedQuestion;
import com.app.model.Question;
import com.app.model.Subject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PassedQuestionDAOImpl extends AbstractDAOImpl<PassedQuestion> implements PassedQuestionDAO {

    private static final Logger logger = Logger.getLogger(PassedQuestionDAOImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.passed_question (passed_question_id, passed_test_id, question_id, user_answer) VALUES (NULL, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.passed_question SET question_id = ?, user_answer= ? WHERE passed_question_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.passed_question WHERE passed_question_id = ? AND question_id = ? AND user_answer = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT p.passed_question_id, p.question_id, p.user_answer, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer, s.name FROM studenttest_app.passed_question p INNER JOIN studenttest_app.question q ON p.question_id = q.question_id INNER JOIN studenttest_app.subject s ON q.subject_id = s.subject_id WHERE p.passed_question_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT p.passed_question_id, p.question_id, p.user_answer, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer, s.name FROM studenttest_app.passed_question p INNER JOIN studenttest_app.question q ON p.question_id = q.question_id INNER JOIN studenttest_app.subject s ON q.subject_id = s.subject_id";

    public PassedQuestionDAOImpl(Connection connection) {
        super(connection);
    }


    @Override
    public void insertAll(Set<PassedQuestion> passedQuestionSet) {
        Set<Long> generatedIdSet = new HashSet<>();
        try (PreparedStatement preparedStatement = getInsertAllStatement(connection, passedQuestionSet)) {
            preparedStatement.executeBatch();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    System.out.println(id);
                    generatedIdSet.add(id);
                }
            }
        } catch (SQLException e) {
            logger.error("Couldn't insert all passed question Set");
            throw new InteractionDBException("Couldn't insert all passed question Set", e);
        }
    }

    public PreparedStatement getInsertAllStatement(Connection connection, Set<PassedQuestion> passedQuestionSet) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        for (PassedQuestion passedQuestion : passedQuestionSet) {
            preparedStatement.setLong(1, passedQuestion.getPassedTest().getId());
            preparedStatement.setLong(2, passedQuestion.getQuestion().getId());
            preparedStatement.setString(3, passedQuestion.getUserAnswer());
            preparedStatement.addBatch();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, passedQuestion.getQuestion().getId());
        preparedStatement.setString(2, passedQuestion.getUserAnswer());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, passedQuestion.getQuestion().getId());
        preparedStatement.setString(2, passedQuestion.getUserAnswer());
        preparedStatement.setLong(3, passedQuestion.getId());
        return preparedStatement;
    }


    @Override
    PreparedStatement getDeleteStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, passedQuestion.getId());
        preparedStatement.setLong(2, passedQuestion.getQuestion().getId());
        preparedStatement.setString(3, passedQuestion.getUserAnswer());
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

    @Override
    PassedQuestion extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PassedQuestion passedQuestion = new PassedQuestion();
        passedQuestion.setId(resultSet.getLong("p.passed_question_id"));
        passedQuestion.setQuestion(new Question(resultSet.getLong("p.question_id"), new Subject(resultSet.getLong("q.subject_id"), resultSet.getString("s.name")), resultSet.getString("q.query"), resultSet.getString("q.answer_1"), resultSet.getString("q.answer_2"), resultSet.getString("q.answer_3"), resultSet.getString("q.answer_4"), resultSet.getString("correct_answer")));
        passedQuestion.setUserAnswer(resultSet.getString("p.user_answer"));
        return passedQuestion;
    }

    @Override
    Set<PassedQuestion> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return super.extractSetOfEntityFromResultSet(resultSet);
    }
}
