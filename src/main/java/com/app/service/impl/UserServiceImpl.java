package com.app.service.impl;

import com.app.dao.AbstractDAO;
import com.app.dao.factory.DAOFactory;
import com.app.dao.UserDAO;
import com.app.dao.connection.DAOConnection;
import com.app.exceptions.PasswordValidationException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.util.PasswordSecurity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.INSTANCE;
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

    @Override
    public User assembleCredentials(User user, String rawPassword) {
        String saltedHash = PasswordSecurity.generateSaltedPasswordHash(rawPassword);
        user.setHash(PasswordSecurity.getHashFromSaltedHash(saltedHash));
        user.setSalt(PasswordSecurity.getSaltFromSaltedHash(saltedHash));
        return user;
    }

    @Override
    protected AbstractDAO<User> getConcreteDAO(DAOConnection daoConnection) {
        return daoFactory.getUserDAO(daoConnection);
    }

    private static class UserServiceImplHolder {
        private final static UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
