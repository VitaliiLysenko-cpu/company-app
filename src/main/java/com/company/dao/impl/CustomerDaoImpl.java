package com.company.dao.impl;

import com.company.dao.CustomerDao;
import com.company.model.Customer;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDaoImpl implements CustomerDao {
    private final Logger logger = Logger.getAnonymousLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSession().getSessionFactory();
    Transaction transaction = null;

    @Override
    public void create(Customer customer) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't create new customer", e);
        }
    }

    @Override
    public Customer read(Long id) {
        Customer customer = new Customer();
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            customer = session.byId(Customer.class).getReference(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't read customer", e);
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't update customer", e);
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't delete customer", e);
        }
    }

    private Session getSession() {
        return this.sessionFactory.openSession();
    }
}
