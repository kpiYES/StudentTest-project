package com.app.dao.mySQLImpl;

import com.app.dao.RoleDAO;
import com.app.exceptions.InteractionDBException;
import com.app.model.Question;
import com.app.model.Role;
import com.app.util.DataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class RoleDAOImpl extends AbstractDAOImpl<Role> implements RoleDAO {

    private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO studenttest_app.role  (role_id, name) VALUES (NULL, ?)";

    private static final String UPDATE_QUERY = "UPDATE studenttest_app.role r SET r.name = ? WHERE r.role_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM studenttest_app.role r WHERE r.role_id = ? AND r.name = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT r.role_id, r.name FROM studenttest_app.role r WHERE r.role_id = ?";

    private static final String FIND_ALL_QUERY = "SELECT r.role_id, r.name FROM studenttest_app.role r";

    private static final String FIND_BY_NAME = "SELECT r.role_id, r.name FROM studenttest_app.role r WHERE r.name = ?";


    public RoleDAOImpl(Connection connection){
        super(connection);
    }

@Override
    public Role findByName(String name) {
        try (PreparedStatement preparedStatement = getFindByNameStatement(connection, name);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("InteractionDBException:Couldn't find Role by name");
            throw new InteractionDBException("Couldn't find Role by name", e);
        }
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
    PreparedStatement getFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_QUERY);
    }

   private PreparedStatement getFindByNameStatement(Connection connection, String name) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @Override
    Role extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        if (resultSet.next()) {
            role.setId(resultSet.getLong("role_id"));
            role.setName(resultSet.getString("name"));
        }
        return role;
    }

    @Override
    Set<Role> extractSetOfEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return super.extractSetOfEntityFromResultSet(resultSet);
    }
}
