package com.app.dao.mySQLImpl;

import com.app.dao.QuestionDAO;
import com.app.model.Question;
import com.app.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAOImpl extends AbstractDAOImpl<Question> implements QuestionDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.question (question_id, subject_id, query, answer_1, answer_2, answer_3, answer_4, correct_answer)" +
            " VALUES (NULL, ?, ?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.question SET subject_id = ?, query = ?, answer_1 = ?, answer_2 = ?, answer_3 = ?, answer_4 = ?, correct_answer = ? WHERE question_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.question WHERE question_id = ? AND subject_id = ? AND query = ? AND answer_1 = ? AND answer_2 = ? AND answer_3 = ? AND answer_4 = ? AND correct_answer = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT q.question_id, q.subject_id, s.name, q.query, q.answer_1, q.answer_2, q.answer_3, q.answer_4, q.correct_answer" +
            " FROM studenttest_app.question q INNER JOIN studenttest_app.subject s" +
            " ON q.subject_id = s.subject_id" +
            " WHERE q.question_id = ?";

    @Override
    public Long insert(Question question) {
        return super.insert(question);
    }

    @Override
    public void update(Question question) {
        super.update(question);
    }

    @Override
    public void delete(Question question) {
        super.delete(question);
    }

    @Override
    public Question findById(Long id) {
        return super.findById(id);
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, Question question) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, question.getSubject().getId());
        preparedStatement.setString(2, question.getQuery());
        preparedStatement.setString(3, question.getAnswer1());
        preparedStatement.setString(4, question.getAnswer2());
        preparedStatement.setString(5, question.getAnswer3());
        preparedStatement.setString(6, question.getAnswer4());
        preparedStatement.setInt(7, question.getCorrectAnswer());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, Question question) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, question.getSubject().getId());
        preparedStatement.setString(2, question.getQuery());
        preparedStatement.setString(3, question.getAnswer1());
        preparedStatement.setString(4, question.getAnswer2());
        preparedStatement.setString(5, question.getAnswer3());
        preparedStatement.setString(6, question.getAnswer4());
        preparedStatement.setInt(7, question.getCorrectAnswer());
        preparedStatement.setLong(8, question.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, Question question) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, question.getId());
        preparedStatement.setLong(2, question.getSubject().getId());
        preparedStatement.setString(3, question.getQuery());
        preparedStatement.setString(4, question.getAnswer1());
        preparedStatement.setString(5, question.getAnswer2());
        preparedStatement.setString(6, question.getAnswer3());
        preparedStatement.setString(7, question.getAnswer4());
        preparedStatement.setInt(8, question.getCorrectAnswer());
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindByIdStatement(Connection connection, Long question_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, question_id);
        return preparedStatement;
    }

    @Override
    Question extractFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        if (resultSet.next()) {
            question.setId(resultSet.getLong("q.question_id"));
            question.setSubject(new Subject(resultSet.getLong("q.subject_id"), resultSet.getString("s.name")));
            question.setQuery(resultSet.getString("q.query"));
            question.setAnswer1(resultSet.getString("q.answer_1"));
            question.setAnswer2(resultSet.getString("q.answer_2"));
            question.setAnswer3(resultSet.getString("q.answer_3"));
            question.setAnswer4(resultSet.getString("q.answer_4"));
            question.setCorrectAnswer(resultSet.getInt("q.correct_answer"));
        }
        return question;
    }
}
