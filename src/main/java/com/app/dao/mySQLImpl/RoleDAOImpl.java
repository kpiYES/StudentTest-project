package com.app.dao.mySQLImpl;

import com.app.dao.IRoleDAO;
import com.app.model.Role;
import com.app.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl implements IRoleDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.role (role_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.role SET name = ? WHERE role_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.role WHERE role_id = ? AND name = ?";

    private static final String GET_BY_ID_QUERY = "SELECT role_id, name FROM studenttest_app.role WHERE role_id = ?";


    @Override
    public Long insert(Role role) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getInsertStatement(connection, role);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role role) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getUpdateStatement(connection, role)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Role role) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = getDeleteStatement(connection, role)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role getById(Long role_id) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement preparedStatement = getByIdStatement(connection, role_id);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                return extractRoleFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement getInsertStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setString(1, role.getName());
        return preparedStatement;
    }

    private PreparedStatement getUpdateStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, role.getName());
        preparedStatement.setLong(2, role.getId());
        return preparedStatement;
    }

    private PreparedStatement getDeleteStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, role.getId());
        preparedStatement.setString(2, role.getName());
        return preparedStatement;
    }

    private PreparedStatement getByIdStatement(Connection connection, Long role_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
        preparedStatement.setLong(1, role_id);
        return preparedStatement;
    }

    private Role extractRoleFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        if (resultSet.next()) {
            role.setId(resultSet.getLong("role_id"));
            role.setName(resultSet.getString("name"));
        }
        return role;
    }
}
