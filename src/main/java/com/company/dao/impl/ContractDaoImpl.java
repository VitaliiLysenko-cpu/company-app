package com.company.dao.impl;

import com.company.dao.ContractDao;
import com.company.model.Contract;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractDaoImpl implements ContractDao {
    private final Logger logger = Logger.getAnonymousLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSession().getSessionFactory();
    private Transaction transaction = null;

    @Override
    public void create(Contract contract) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.persist(contract);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't create new contract", e);
        }
    }

    @Override
    public Contract read(Long id) {
        Contract contract = new Contract();
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            contract = session.byId(Contract.class).getReference(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't read contract", e);
        }
        return contract;
    }

    @Override
    public void update(Contract contract) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(contract);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't update contract", e);
        }
    }

    @Override
    public void delete(Contract contract) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(contract);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't delete contract", e);
        }
    }

    private Session getSession() {
        return this.sessionFactory.openSession();
    }
}
