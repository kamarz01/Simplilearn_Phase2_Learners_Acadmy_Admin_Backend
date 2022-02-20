package com.kamar.learnersacademybackend.service.Impl;

import com.kamar.learnersacademybackend.config.HibernateSessionFactory;
import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.dto.ClassDTO;
import com.kamar.learnersacademybackend.service.ClassService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassServiceImpl implements ClassService {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void createClass(Class _class) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(_class);
        transaction.commit();
        session.close();
    }

    @Override
    public Class getClassById(int classId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Class data =  session.get(Class.class,classId);
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Class> getAllClasses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Class> data = session.createQuery("from Class").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public  List<ClassDTO> getAllClassesWithAllData() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Class> subjectData = session.createQuery("select t from Class t left join fetch t.subjects").list();
        List<Class> teacherData = session.createQuery("select t from Class t left join fetch t.teachers").list();
        List<Class> studentData = session.createQuery("select t from Class t left join fetch t.students").list();
        List<ClassDTO> data = new ArrayList<>();
        List<Class> classList = Stream.of(subjectData, teacherData,studentData).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        ModelMapper modelMapper = new ModelMapper();
        List<ClassDTO> finalData = classList
                .stream()
                .map(_class -> modelMapper.map(_class, ClassDTO.class))
                .collect(Collectors.toList());
        transaction.commit();
        session.close();
        return finalData;
    }

    @Override
    public List<Class> getAllClassesWithSubjects() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Class> data = session.createQuery("select t from Class t left join fetch t.subjects").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Class> getAllClassesWithTeachers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Class> data = session.createQuery("select t from Class t left join fetch t.teachers").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public List<Class> getAllClassesWithStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Class> data = session.createQuery("select t from Class t left join fetch t.students").list();
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public void addStudentToClass(int classId, Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Class data = session.get(Class.class,classId);
        if (data != null){
            data.getStudents().add(student);
            session.update(data);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void addTeacherToClass(int classId, Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Class data = session.get(Class.class,classId);
        if (data != null){
            data.getTeachers().add(teacher);
            session.update(data);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void updateClass(Class _class) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(_class);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeClass(int classId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Class deletedClass = new Class();
        deletedClass.setClassId(classId);
        session.delete(deletedClass);
        transaction.commit();
        session.close();
    }
}
