package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.RoleDAO;
import com.app.model.Role;
import com.app.service.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private RoleDAO roleDAO = daoFactory.getRoleDAO();

    @Override
    public Long insert(Role role) {
        return roleDAO.insert(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }


    @Override
    public void delete(Role role) {
roleDAO.delete(role);
    }

    @Override
    public Role getById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public Set<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }
}
