package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.QuestionDAO;
import com.app.dao.SubjectDAO;
import com.app.model.Subject;
import com.app.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {


    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private SubjectDAO subjectDAO = daoFactory.getSubjectDAO();



    @Override
    public Long insert(Subject subject) {
        return null;
    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void delete(Subject subject) {

    }

    @Override
    public Subject findById(Long id) {
        return null;
    }

    @Override
    public Subject findByName(String name) {

        Subject subject = subjectDAO.findByName(name);

        return subject;
    }
}
