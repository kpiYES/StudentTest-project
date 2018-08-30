package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.RoleDAO;
import com.app.dao.connection.DAOConnection;
import com.app.model.Role;
import com.app.service.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {


    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private RoleServiceImpl() {
    }

    public static RoleServiceImpl getInstance() {
        return RoleServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Role role) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            return roleDAO.insert(role);
        }
    }

    @Override
    public void update(Role role) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            roleDAO.update(role);
        }
    }

    @Override
    public void delete(Role role) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            roleDAO.delete(role);
        }
    }

    @Override
    public Role findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            return roleDAO.findById(id);
        }
    }

    @Override
    public Set<Role> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            return roleDAO.findAll();
        }
    }

    @Override
    public Role findByName(String name) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            RoleDAO roleDAO = daoFactory.getRoleDAO(daoConnection);
            return roleDAO.findByName(name);
        }
    }

    private static class RoleServiceImplHolder {
        private final static RoleServiceImpl INSTANCE = new RoleServiceImpl();
    }
}
