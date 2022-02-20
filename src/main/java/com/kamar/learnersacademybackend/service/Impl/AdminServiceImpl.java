package com.kamar.learnersacademybackend.service.Impl;

import com.kamar.learnersacademybackend.config.HibernateSessionFactory;
import com.kamar.learnersacademybackend.entity.Admin;
import com.kamar.learnersacademybackend.service.AdminService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AdminServiceImpl implements AdminService {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public Admin getAdmin(String username, String password) {
        String query = "select a from Admin a where a.adminUserName = :user and a.adminPassword = :password";
        Admin data = null;
        try {
            data = sessionFactory.openSession().createQuery(query, Admin.class)
                    .setParameter("user", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {

        }
        return data;
    }

    @Override
    public void createAdmin(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }
}
