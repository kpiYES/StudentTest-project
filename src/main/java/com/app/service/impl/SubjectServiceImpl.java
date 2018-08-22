package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.SubjectDAO;
import com.app.model.Subject;
import com.app.service.SubjectService;

import java.util.Set;

public class SubjectServiceImpl implements SubjectService {


    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private SubjectDAO subjectDAO = daoFactory.getSubjectDAO();

    @Override
    public Long insert(Subject subject) {
        return subjectDAO.insert(subject);
    }

    @Override
    public void update(Subject subject) {
        subjectDAO.update(subject);
    }

    @Override
    public void delete(Subject subject) {
        subjectDAO.delete(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectDAO.findById(id);
    }



    @Override
    public Set<Subject> findAll() {
        return subjectDAO.findAll();
    }

    @Override
    public Subject findByName(String name) {
        return subjectDAO.findByName(name);
    }
}
