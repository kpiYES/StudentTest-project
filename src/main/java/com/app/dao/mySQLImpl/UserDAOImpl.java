package com.app.dao.mySQLImpl;

import com.app.dao.UserDAO;
import com.app.model.Role;
import com.app.model.User;
import com.app.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.user (user_id, role_id, first_name, last_name, mail, salt, hash)" +
            " VALUES (NULL, ?, ?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.user SET role_id = ?, first_name = ?, last_name = ?, mail = ?, salt = ?, hash = ? WHERE user_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.user WHERE user_id = ? AND role_id = ? AND first_name = ? AND last_name = ? AND mail = ? AND salt = ? AND hash = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT user_id,role_id,first_name,last_name,mail,salt,hash FROM studenttest_app.user WHERE user_id = ?";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT u.user_id, u.role_id, r.name, u.first_name, u.last_name, u.mail, u.salt, u.hash" +
            " FROM studenttest_app.user u INNER JOIN studenttest_app.role r" +
            " ON u.role_id = r.role_id" +
            " WHERE u.mail = ?";

    @Override
    public Long insert(User user) {
        return super.insert(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public User findByMail(String mail) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getFindByMailStatement(connection, mail);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get user by mail", e);
        }
    }

    PreparedStatement getInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFistName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        return preparedStatement;
    }

    PreparedStatement getUpdateStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFistName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        preparedStatement.setLong(7, user.getId());
        return preparedStatement;
    }

    PreparedStatement getDeleteStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getRole().getId());
        preparedStatement.setString(3, user.getFistName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getMail());
        preparedStatement.setString(6, user.getSalt());
        preparedStatement.setString(7, user.getHash());
        return preparedStatement;
    }

    PreparedStatement getFindByIdStatement(Connection connection, Long user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, user_id);
        return preparedStatement;
    }

    private PreparedStatement getFindByMailStatement(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("u.user_id"));
            user.setRole(new Role(resultSet.getLong("u.role_id"), resultSet.getString("r.name")));
            user.setFistName(resultSet.getString("u.first_name"));
            user.setLastName(resultSet.getString("u.last_name"));
            user.setMail(resultSet.getString("u.mail"));
            user.setSalt(resultSet.getString("u.salt"));
            user.setHash(resultSet.getString("u.hash"));
        }
        return user;
    }
}
