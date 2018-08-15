package com.app.dao.mySQLImpl;

import com.app.dao.RoleDAO;
import com.app.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl extends AbstractDAOImpl<Role> implements RoleDAO {

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.role (role_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.role SET name = ? WHERE role_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.role WHERE role_id = ? AND name = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT role_id, name FROM studenttest_app.role WHERE role_id = ?";


    @Override
    public Long insert(Role role) {
        return super.insert(role);
    }

    @Override
    public void update(Role role) {
        super.update(role);
    }

    @Override
    public void delete(Role role) {
        super.delete(role);
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id);
    }

    @Override
    PreparedStatement getInsertStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, role.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, role.getName());
        preparedStatement.setLong(2, role.getId());
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection connection, Role role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, role.getId());
        preparedStatement.setString(2, role.getName());
        return preparedStatement;
    }

    @Override
    PreparedStatement getFindByIdStatement(Connection connection, Long role_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setLong(1, role_id);
        return preparedStatement;
    }

    @Override
    Role extractFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        if (resultSet.next()) {
            role.setId(resultSet.getLong("role_id"));
            role.setName(resultSet.getString("name"));
        }
        return role;
    }
}
