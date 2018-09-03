package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.SubjectDAO;
import com.app.model.Subject;
import com.app.service.SubjectService;

import java.util.Set;

public class SubjectServiceImpl implements SubjectService {

    private SubjectDAO subjectDAO;

    private SubjectServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        subjectDAO = daoFactory.getSubjectDAO();
    }

    public static SubjectServiceImpl getInstance() {
        return SubjectServiceImplHolder.INSTANCE;
    }

    @Override
    public Subject findById(Long id) {
            return subjectDAO.findById(id);
        }

    @Override
    public Set<Subject> findAll() {
            return subjectDAO.findAll();
    }

    private static class SubjectServiceImplHolder {
        private final static SubjectServiceImpl INSTANCE = new SubjectServiceImpl();
    }
}
