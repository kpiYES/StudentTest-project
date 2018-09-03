package com.app.dao.mySQLImpl;

import com.app.dao.PassedQuestionDAO;
import com.app.dao.connection.ConnectionSource;
import com.app.exceptions.InteractionDBException;
import com.app.model.PassedQuestion;
import com.app.model.Question;
import com.app.model.Subject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PassedQuestionDAOMySQLImpl extends AbstractDAOMySQLImpl<PassedQuestion> implements PassedQuestionDAO {

    private static final Logger logger = Logger.getLogger(PassedQuestionDAOMySQLImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.passed_question (passed_question_id, passed_test_id, question_id, user_answer) VALUES (NULL, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.passed_question SET question_id = ?, user_answer= ? WHERE passed_question_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.passed_question WHERE passed_question_id = ? AND question_id = ? AND user_answer = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT p.passed_question_id, p.question_id, p.user_answer, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer, s.name FROM studenttest_app.passed_question p INNER JOIN studenttest_app.question q ON p.question_id = q.question_id INNER JOIN studenttest_app.subject s ON q.subject_id = s.subject_id WHERE p.passed_question_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT p.passed_question_id, p.question_id, p.user_answer, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer, s.name FROM studenttest_app.passed_question p INNER JOIN studenttest_app.question q ON p.question_id = q.question_id INNER JOIN studenttest_app.subject s ON q.subject_id = s.subject_id";

    private static final String FIND_ALL_BY_PASSED_TEST_ID_QUERY = "SELECT p.passed_question_id, p.question_id, p.user_answer, q.subject_id, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer, s.name FROM studenttest_app.passed_question p INNER JOIN studenttest_app.question q ON p.question_id = q.question_id INNER JOIN studenttest_app.subject s ON q.subject_id = s.subject_id WHERE p.passed_test_id = ?";

    private PassedQuestionDAOMySQLImpl() {
    }

    public static PassedQuestionDAOMySQLImpl getInstance() {
        return PassedQuestionDAOMySQLImplHolder.INSTANCE;
    }

    @Override
    public List<Long> insertAll(Set<PassedQuestion> passedQuestionSet) {
        List<Long> generatedIdList = new ArrayList<>(passedQuestionSet.size());
        Connection connection = ConnectionSource.getConnection();
        try (PreparedStatement preparedStatement = getInsertAllStatement(connection, passedQuestionSet)) {
            preparedStatement.executeBatch();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                generatedIdList.add(id);
            }
            return generatedIdList;
        } catch (SQLException e) {
            logger.error("Couldn't insert all passed question Set");
            throw new InteractionDBException("Couldn't insert all passed question Set", e);
        }
    }

    @Override
    public Set<PassedQuestion> findAllByPassedTestId(Long id) {
        Connection connection = ConnectionSource.getConnection();
        try (PreparedStatement preparedStatement = getFindAllByPassedTestIdStatement(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractSetOfEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't find Passed Question Set by Test Id");
            throw new InteractionDBException("Couldn't find Passed Question Set by Test Id", e);
        }
    }

    private PreparedStatement getFindAllByPassedTestIdStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PASSED_TEST_ID_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    private PreparedStatement getInsertAllStatement(Connection connection, Set<PassedQuestion> passedQuestionSet) throws SQLException {
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
    protected PreparedStatement getInsertStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, passedQuestion.getQuestion().getId());
        preparedStatement.setString(2, passedQuestion.getUserAnswer());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, passedQuestion.getQuestion().getId());
        preparedStatement.setString(2, passedQuestion.getUserAnswer());
        preparedStatement.setLong(3, passedQuestion.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(Connection connection, PassedQuestion passedQuestion) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, passedQuestion.getId());
        preparedStatement.setLong(2, passedQuestion.getQuestion().getId());
        preparedStatement.setString(3, passedQuestion.getUserAnswer());
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
    protected PassedQuestion extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PassedQuestion passedQuestion = new PassedQuestion();
        if (resultSet.next()) {
            passedQuestion.setId(resultSet.getLong("p.passed_question_id"));
            passedQuestion.setQuestion(new Question(resultSet.getLong("p.question_id"), new Subject(resultSet.getLong("q.subject_id"), resultSet.getString("s.name")), resultSet.getString("q.query"), resultSet.getString("q.answer_1"), resultSet.getString("q.answer_2"), resultSet.getString("q.answer_3"), resultSet.getString("q.answer_4"), resultSet.getString("q.correct_answer")));
            passedQuestion.setUserAnswer(resultSet.getString("p.user_answer"));
        }
        return passedQuestion;

    }

    private static class PassedQuestionDAOMySQLImplHolder {
        private final static PassedQuestionDAOMySQLImpl INSTANCE = new PassedQuestionDAOMySQLImpl();
    }

}
