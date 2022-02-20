package com.kamar.learnersacademybackend.service.Impl;

import com.kamar.learnersacademybackend.config.HibernateSessionFactory;
import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.service.TeacherService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void createTeacher(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(teacher);
        transaction.commit();
        session.close();
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Teacher data = (Teacher) session.get(Teacher.class,teacherId);
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Teacher> data = session.createQuery("from Teacher ").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Teacher> getAllTeachersWithSubjects() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Teacher> data = session.createQuery("select t from Teacher t left join fetch t.subjects").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Teacher> getAllTeachersNotAssigned() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Teacher> data = session.createQuery("from Teacher").list();
        List<Teacher> teachersNotAssigned = new ArrayList<>();
        for (Teacher teacher:data) {
            if (teacher.getClasses() == null)
                teachersNotAssigned.add(teacher);
        }
        transaction.commit();
        session.close();
        return teachersNotAssigned;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(teacher);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeTeacher(int teacherId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Teacher data = session.get(Teacher.class,teacherId);
        // Delete any relations - Classes
        Class _class =  data.getClasses();
        if (_class != null){
            _class.getTeachers().remove(data);
            session.update(_class);
        }
        session.delete(data);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeTeacherFromClass(int teacherId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Teacher data = session.get(Teacher.class,teacherId);
        // Delete any relations - Classes
        Class _class =  data.getClasses();
        if (_class != null){
            _class.getTeachers().remove(data);
            session.update(data);
        }
        transaction.commit();
        session.close();
    }
}
