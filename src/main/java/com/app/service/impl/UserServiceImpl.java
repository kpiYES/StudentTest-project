package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.UserDAO;
import com.app.exceptions.PasswordValidationException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.util.PasswordSecurity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

public class UserServiceImpl implements UserService {

   private UserDAO userDAO;

    private UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
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
    public Set<User> findAll() {
            return userDAO.findAll();
        }

    @Override
    public User findByMail(String mail) {
            return userDAO.findByMail(mail);
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
