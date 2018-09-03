package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.UserDAO;
import com.app.dao.connection.DAOConnection;
import com.app.exceptions.PasswordValidationException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.util.PasswordSecurity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(User user) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            return userDAO.insert(user);
        }
    }

    @Override
    public void update(User user) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            userDAO.update(user);
        }
    }

    @Override
    public void delete(User user) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            userDAO.delete(user);
        }
    }

    @Override
    public User findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            return userDAO.findById(id);
        }
    }

    @Override
    public Set<User> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            return userDAO.findAll();
        }
    }

    @Override
    public User findByMail(String mail) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(daoConnection);
            return userDAO.findByMail(mail);
        }
    }

    @Override
    public boolean validateUserPassword(String password, User user) {
        try {
            return PasswordSecurity.validatePassword(password, user.getHash(), user.getSalt());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new PasswordValidationException("Invalid password exception", e);
        }
    }

    private static class UserServiceImplHolder {
        private final static UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
