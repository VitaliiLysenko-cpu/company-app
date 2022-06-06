package com.company.dao.impl;

import com.company.dao.CardDao;
import com.company.model.Card;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDaoImpl implements CardDao {
    private final Logger logger = Logger.getAnonymousLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSession().getSessionFactory();
    private Transaction transaction = null;

    @Override
    public void create(Card card) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.persist(card);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE,"Exception: couldn't create new card",e);
        }
    }

    @Override
    public Card read(Long id) {
        Card card = new Card();
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            card = session.byId(Card.class).getReference(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE,"Exception: couldn't read card",e);
        }
        return card;
    }

    @Override
    public void update(Card card) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(card);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE,"Exception: couldn't update card",e);
        }
    }

    private Session getSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public void delete(Card card) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(card);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE,"Exception: couldn't delete card",e);
        }
    }
}
