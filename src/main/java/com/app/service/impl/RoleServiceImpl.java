package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.RoleDAO;
import com.app.model.Role;
import com.app.service.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    private RoleServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        roleDAO = daoFactory.getRoleDAO();
    }

    public static RoleServiceImpl getInstance() {
        return RoleServiceImplHolder.INSTANCE;
    }

    @Override
    public Set<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }

    private static class RoleServiceImplHolder {
        private final static RoleServiceImpl INSTANCE = new RoleServiceImpl();
    }
}
