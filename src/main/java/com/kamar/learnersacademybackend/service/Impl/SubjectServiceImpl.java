package com.kamar.learnersacademybackend.service.Impl;

import com.kamar.learnersacademybackend.config.HibernateSessionFactory;
import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.service.SubjectService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void createSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subject);
        transaction.commit();
        session.close();
    }

    @Override
    public Subject getSubjectById(int subjectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Subject data = (Subject) session.get(Subject.class,subjectId);
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Subject> getAllSubject() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Subject> data = session.createQuery("from Subject").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public void updateSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subject);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeSubject(int subjectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Subject data = session.get(Subject.class,subjectId);
        // Delete any relations - Classes
        List<Class> classes =  data.getClasses();
        for (Class c:classes) {
            c.getSubjects().remove(data);
            session.update(c);
        }
        session.delete(data);
        transaction.commit();
        session.close();
    }
}
