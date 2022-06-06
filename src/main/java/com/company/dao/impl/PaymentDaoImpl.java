package com.company.dao.impl;

import com.company.dao.PaymentDao;
import com.company.model.Payment;
import com.company.util.HibernateUtil;
import jakarta.persistence.PostUpdate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDaoImpl implements PaymentDao {
    private final Logger logger = Logger.getAnonymousLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSession().getSessionFactory();
    private Transaction transaction = null;

    @Override
    public void create(Payment payment) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't create new payment", e);
        }
    }

    @Override
    public Payment read(Long id) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Payment payment = session.byId(Payment.class).getReference(id);
            payment.getPaymentId();
            payment.getAmountOfMoney();
            payment.getTimestamp();
            transaction.commit();
            return payment;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't read payment", e);
        }
        return null;
    }

    @Override
    @PostUpdate
    public void update(Payment payment) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't update payment", e);
        }
    }

    @Override
    public void delete(Payment payment) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't delete payment", e);
        }
    }

    @Override
    public List<Payment> findAllByContract(Long contractId) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            List payments;
            payments = session
                    .createSelectionQuery("from Payment as P where P.contract.id=:id")
                    .setParameter("id", contractId)
                    .list();
            return payments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception: couldn't find payment by contract", e);
        }
        return null;
    }

    @Override
    public List<Payment> findAllByCustomer(Long customerId) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hqlString = "from Payment p inner join Contract c on p.contract.contractId = c.contractId where c.customer.customerId = :customerId";
            List<Payment> payments = (List<Payment>) session
                    .createSelectionQuery(hqlString)
                    .setParameter("customerId", customerId)
                    .list();
            transaction.commit();
            return payments;
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't find payment by customer", e);
        }
        return null;
    }

    @Override
    public List<Payment> findAllAmountMoreThan(BigDecimal amount) {
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            List payments;
            payments = session.createSelectionQuery(" from Payment p inner join Contract c where p.amountOfMoney > :amount order by c.customer.id ")
                    .setParameter("amount", amount)
                    .list();
            transaction.commit();
            return payments;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Exception: couldn't find payment where amount more than", e);
        }
        return null;
    }

    private Session getSession() {
        return this.sessionFactory.openSession();
    }
}
