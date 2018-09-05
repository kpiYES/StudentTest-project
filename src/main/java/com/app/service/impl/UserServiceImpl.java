package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.UserDAO;
import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.RoleService;
import com.app.service.ServiceFactory;
import com.app.service.UserService;

import java.util.Set;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RoleService roleService;

    private UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        roleService = ServiceFactory.getRoleService();
        userDAO = daoFactory.getUserDAO();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public Set<UserDTO> findAll() {
        Set<User> userSet = userDAO.findAll();
        return DTOHandler.constructSetUserDTO(userSet);
    }

    @Override
    public User findByMail(String mail) {
        return userDAO.findByMail(mail);
    }

    @Override
    public UserDTO updateRole(String newUserRole, UserDTO userDTO) {
        Role role = roleService.findByName(newUserRole);
        User user = findById(userDTO.getId());
        user.setRole(role);
        update(user);
        return DTOHandler.constructUserDTO(user);
    }

    private static class UserServiceImplHolder {
        private final static UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
