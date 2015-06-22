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

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/22.
 */
public abstract class HibernateSupport {

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
            session.close();
        }
        return result;
    }

    protected Object get(final String entity,final String property, final Object value) {
        Object result = null;
        result = openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                return session.createQuery(String.format("from %s where %s = ?", entity, property))
                              .setParameter(0, value)
                              .uniqueResult();
            }
        });
        return result;
    }

    protected Object get(final Class c,final Serializable value){
        return openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                return session.get(c , value);
            }
        });
    }
}
