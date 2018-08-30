package com.app.dao.mySQLImpl;

import com.app.dao.UserDAO;
import com.app.exceptions.InteractionDBException;
import com.app.model.Role;
import com.app.model.User;
import com.app.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.user (user_id, role_id, first_name, last_name, mail, salt, hash) VALUES (NULL, ?, ?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.user SET role_id = ?, first_name = ?, last_name = ?, mail = ?, salt = ?, hash = ? WHERE user_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.user WHERE user_id = ? AND role_id = ? AND first_name = ? AND last_name = ? AND mail = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT u.user_id, u.role_id, r.name, u.first_name, u.last_name, u.mail, u.salt, u.hash FROM studenttest_app.user u INNER JOIN studenttest_app.role r ON u.role_id = r.role_id WHERE u.user_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT u.user_id, u.role_id, r.name, u.first_name, u.last_name, u.mail, u.salt, u.hash FROM studenttest_app.user u INNER JOIN studenttest_app.role r ON u.role_id = r.role_id";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT u.user_id, u.role_id, r.name, u.first_name, u.last_name, u.mail, u.salt, u.hash FROM studenttest_app.user u INNER JOIN studenttest_app.role r ON u.role_id = r.role_id WHERE u.mail = ?";

    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findByMail(String mail) {
        Assert.isNotNull(mail, "mail must not be null");
        try (PreparedStatement preparedStatement = getFindByMailStatement(connection, mail);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Couldn't find User by mail: {}", mail);
            throw new InteractionDBException("Couldn't find User by mail", e);
        }
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setLong(1, user.getRole().getId());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getMail());
        preparedStatement.setString(5, user.getSalt());
        preparedStatement.setString(6, user.getHash());
        preparedStatement.setLong(7, user.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getRole().getId());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getMail());
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindByIdStatement(Connection connection, Long user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, user_id);
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_QUERY);
    }

    private PreparedStatement getFindByMailStatement(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    @Override
    User extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("u.user_id"));
            user.setRole(new Role(resultSet.getLong("u.role_id"), resultSet.getString("r.name")));
            user.setFirstName(resultSet.getString("u.first_name"));
            user.setLastName(resultSet.getString("u.last_name"));
            user.setMail(resultSet.getString("u.mail"));
            user.setSalt(resultSet.getString("u.salt"));
            user.setHash(resultSet.getString("u.hash"));
        }
        return user;
    }
}
