package com.kamar.learnersacademybackend.service.Impl;

import com.kamar.learnersacademybackend.config.HibernateSessionFactory;
import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void createStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    @Override
    public Student getStudentById(int studentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student data = (Student) session.get(Student.class,studentId);
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> data = session.createQuery("from Student").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Student> getAllStudentsNotAssigned() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> data = session.createQuery("from Student").list();
        List<Student> studentNotAssigned = new ArrayList<>();
        for (Student std:data) {
            if (std.getClasses() == null)
                studentNotAssigned.add(std);
        }
        transaction.commit();
        session.close();
        return studentNotAssigned;
    }

    @Override
    public void updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeStudent(int studentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student data = session.get(Student.class,studentId);
        // Delete any relations - Classes
        Class _class =  data.getClasses();
        if (_class != null){
            _class.getStudents().remove(data);
            session.update(_class);
        }
        session.delete(data);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeStudentFromClass(int studentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student data = session.get(Student.class,studentId);
        // Delete any relations - Classes
        Class _class =  data.getClasses();
        if (_class != null){
            _class.getStudents().remove(data);
            session.update(data);
        }
        transaction.commit();
        session.close();
    }
}
