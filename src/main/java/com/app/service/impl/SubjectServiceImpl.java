package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.SubjectDAO;
import com.app.dao.connection.DAOConnection;
import com.app.model.Subject;
import com.app.service.SubjectService;

import java.util.Set;

public class SubjectServiceImpl implements SubjectService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private SubjectServiceImpl() {
    }

    public static SubjectServiceImpl getInstance() {
        return SubjectServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Subject subject) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            return subjectDAO.insert(subject);
        }
    }

    @Override
    public void update(Subject subject) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            subjectDAO.update(subject);
        }
    }

    @Override
    public void delete(Subject subject) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            subjectDAO.delete(subject);
        }
    }

    @Override
    public Subject findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            return subjectDAO.findById(id);
        }
    }

    @Override
    public Set<Subject> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            return subjectDAO.findAll();
        }
    }

    @Override
    public Subject findByName(String name) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            SubjectDAO subjectDAO = daoFactory.getSubjectDAO(daoConnection);
            return subjectDAO.findByName(name);
        }
    }

    private static class SubjectServiceImplHolder {
        private final static SubjectServiceImpl INSTANCE = new SubjectServiceImpl();
    }
}
