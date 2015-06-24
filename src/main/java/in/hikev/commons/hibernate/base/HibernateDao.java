package in.hikev.commons.hibernate.base;

import in.hikev.commons.hibernate.HibernateSessionAction;
import in.hikev.commons.hibernate.HibernateSessionFunc;
import in.hikev.commons.hibernate.HibernateTransactionAction;
import in.hikev.commons.hibernate.HibernateTransactionFunc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Administrator on 2015/6/25.
 */
public abstract class HibernateDao {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void openSession(HibernateSessionAction action){
        Session session = sessionFactory.openSession();
        action.execute(session);
        session.close();
    }

    protected Object openSession(HibernateSessionFunc func){
        Session session = sessionFactory.openSession();
        Object result = func.execute(session);
        session.close();
        return result;
    }

    protected void openSession(HibernateTransactionAction action) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            action.execute(session, transaction);
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            if (!transaction.wasCommitted() && !transaction.wasRolledBack()) {
                transaction.commit();
            }
            session.flush();
            session.close();
        }
    }

    protected Object openSession(HibernateTransactionFunc action) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object result = null;
        try {
            result = action.execute(session, transaction);
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            if (!transaction.wasCommitted() && !transaction.wasRolledBack()) {
                transaction.commit();
            }
            session.flush();
            session.close();
        }
        return result;
    }
}
