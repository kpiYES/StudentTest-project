package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.UserDAO;
import com.app.exceptions.PasswordValidationException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.util.PasswordSecurity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public User findByMail(String mail) {

        User user = userDAO.findByMail(mail);

        //for what assert

//        Assert.isNotNull(user, "Error - user is null");
        System.out.println(2.5);
//        Assert.isNotNull(user.getRole(), "Error - role is null");
        System.out.println(3);
        return user;
    }

    @Override
    public boolean validateUserPassword(String password, User user) {

        try {
            return PasswordSecurity.validatePassword(password, user.getHash(), user.getSalt());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new PasswordValidationException("Invalid password exception", e);
        }
    }
}
