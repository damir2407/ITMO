package com.example.weblab3.server.db;

import com.example.weblab3.server.Resulted;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ResultDAO implements ResultDAOImpl {

    public void save(Resulted result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(result);
        transaction.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE From Resulted ").executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<Resulted> getAll() {
        System.out.println(HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from Resulted "));
        List<Resulted> results = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from Resulted ").list();
        return results;

    }
}
