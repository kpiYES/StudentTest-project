package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.UserDAO;
import com.app.exceptions.PasswordValidationException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.util.PasswordSecurity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public Long insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public Set<User> findAll() {
        Set<User> userSet = userDAO.findAll();


        return userSet;
    }

    @Override
    public User findByMail(String mail) {

        User user = userDAO.findByMail(mail);

        //for what assert

//        Assert.isNotNull(user, "Error - user is null");
//        Assert.isNotNull(user.getRole(), "Error - role is null");
        return user;
    }

//    @Override
//    public void updateByRoleName(User user, String roleName) {
//        userDAO.updateByRoleName(user, roleName);
//    }

    @Override
    public boolean validateUserPassword(String password, User user) {
        try {
            return PasswordSecurity.validatePassword(password, user.getHash(), user.getSalt());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new PasswordValidationException("Invalid password exception", e);
        }
    }
}
