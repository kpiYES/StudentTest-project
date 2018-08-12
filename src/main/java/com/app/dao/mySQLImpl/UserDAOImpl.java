package com.app.dao.mySQLImpl;

import com.app.dao.IUserDAO;
import com.app.model.Role;
import com.app.model.User;
import com.app.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.user (user_id, role_id, first_name, last_name, mail, salt, hash)\n" +
            "VALUES (NULL, ?, ?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.user SET role_id = ?, first_name = ?, last_name = ?, mail = ?, salt = ?, hash = ? WHERE user_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.user WHERE user_id = ? AND role_id = ? AND first_name = ? AND last_name = ? AND mail = ? AND salt = ? AND hash = ?";

    private static final String GET_BY_ID_QUERY = "SELECT user_id,role_id,first_name,last_name,mail,salt,hash FROM studenttest_app.user WHERE user_id = ?";

    private static final String GET_BY_EMAIL_QUERY = "SELECT u.user_id,r.role_id, r.name, u.first_name, u.last_name, u.mail, u.salt, u.hash\n" +
            "FROM studenttest_app.user u INNER JOIN studenttest_app.role r\n" +
            "ON u.role_id = r.role_id\n" +
            "WHERE u.mail = ?";

    @Override
    public Long insert(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getInsertStatement(connection, user);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getUpdateStatement(connection, user)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getDeleteStatement(connection, user)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Long user_id) {
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement preparedStatement = getByIdStatement(connection, user_id);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByMail(String email) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getByMailStatement(connection, email);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get user by mail", e);
        }
    }

    private PreparedStatement getInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFistName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        return preparedStatement;
    }

    private PreparedStatement getUpdateStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFistName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        preparedStatement.setLong(7,user.getId());
        return preparedStatement;
    }

    private PreparedStatement getDeleteStatement(Connection connection, User user) throws SQLException {
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

    private PreparedStatement getByIdStatement(Connection connection, Long user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
        preparedStatement.setLong(1, user_id);
        return preparedStatement;
    }

    private PreparedStatement getByMailStatement(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_QUERY);
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("user_id"));
            user.setRole(new Role(resultSet.getLong("role_id"), resultSet.getString("name")));
            user.setFistName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setMail(resultSet.getString("mail"));
            user.setSalt(resultSet.getString("salt"));
            user.setHash(resultSet.getString("hash"));
        }
        return user;
    }
}
