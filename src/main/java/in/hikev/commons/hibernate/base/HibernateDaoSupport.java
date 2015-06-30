package in.hikev.commons.hibernate.base;

import in.hikev.commons.hibernate.HibernateSessionAction;
import in.hikev.commons.hibernate.HibernateSessionFunc;
import in.hikev.commons.hibernate.HibernateTransactionAction;
import in.hikev.commons.hibernate.HibernateTransactionFunc;
import in.hikev.commons.hibernate.model.Entity;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/6/22.
 */
public abstract class HibernateDaoSupport extends HibernateDao {

    protected <T extends Entity> T save(final T entity) {
        Object result = openSession(new HibernateTransactionFunc() {
            public Object execute(Session session, Transaction transaction) {
                return session.save(entity);
            }
        });
        int id = Integer.parseInt(result.toString());
        entity.setId(id);
        return entity;
    }

    protected void update(final Object entity){
        openSession(new HibernateTransactionAction() {
            public void execute(Session session, Transaction transaction) {
                session.update(entity);
            }
        });
    }

    protected void delete(final Object entity){
        openSession(new HibernateTransactionAction() {
            public void execute(Session session, Transaction transaction) {
                session.delete(entity);
            }
        });
    }

    protected <T extends Entity> void delete(final Class<T> c,final int id) {
        T entity = get(c, id);
        delete(entity);
    }

    protected <T extends Entity> T get(final Class<T> c,final String property, final Object value) {
        T result = (T)openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                return session.createQuery(String.format("from %s where %s = ?", c.getSimpleName(), property))
                              .setParameter(0, value)
                              .uniqueResult();
            }
        });
        return result;
    }

    protected <T extends Entity> T get(final Class<T> c,final Serializable value) {
        return (T)openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                return session.get(c, value);
            }
        });
    }

    protected int count(final String hql,final Object ...args) {
        Object result = openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                Query query = session.createQuery(String.format("select count(*) %s",hql));
                if (args != null && args.length > 0) {
                    for (int i = 0; i < args.length; i++) {
                        query.setParameter(i, args[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
        return Integer.parseInt(result.toString());
    }

    protected int count(final String hql) {
        return count(hql, null);
    }

    protected boolean exist(final String hql) {
        return count(hql) > 0;
    }

    protected boolean exist(final String hql,final Object ...args) {
        return count(hql, args) > 0;
    }

    protected <T> boolean exist(final Class<T> c,Object id) {
        return count(String.format("from %s o where o.id = ?", c.getSimpleName()), id) > 0;
    }

    protected <T extends Entity> T querySingle(final String hql){
        return querySingle(hql,null);
    }

    protected <T extends Entity> T querySingle(final String hql,final Object ...args) {
        return (T) openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                Query query = session.createQuery(hql);
                if (args != null && args.length > 0) {
                    for (int i = 0; i < args.length; i++) {
                        query.setParameter(i, args[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
    }

    protected <T extends Entity> ArrayList<T> query(final String hql,final Object ...args) {
        final ArrayList<T> result = new ArrayList<T>();
        openSession(new HibernateSessionAction() {
            public void execute(Session session) {
                Query query = session.createQuery(hql);
                if (args != null && args.length > 0) {
                    for (int i = 0; i < args.length; i++) {
                        query.setParameter(i, args[i]);
                    }
                }
                Iterator it = query.iterate();
                while (it.hasNext()) {

                    result.add((T) it.next());
                }
            }
        });
        return result;
    }

    protected <T extends Entity> ArrayList<T> query(final String hql) {
        return query(hql, null);
    }

    protected <T extends Entity> ArrayList<T> query(final int startIndex,final int maxResult,final String hql) {
        return query(startIndex, maxResult, hql, null);
    }

    protected <T extends Entity> ArrayList<T> query(final int startIndex,final int maxResult,final String hql,final Object ...args) {
        final ArrayList<T> result = new ArrayList<T>();
        openSession(new HibernateSessionAction() {
            public void execute(Session session) {
                Query query = session.createQuery(hql);
                if (args != null && args.length > 0) {
                    for (int i = 0; i < args.length; i++) {
                        query.setParameter(i, args[i]);
                    }
                }
                Iterator it = query.setFirstResult(startIndex)
                                   .setMaxResults(maxResult)
                                   .iterate();
                while (it.hasNext()) {
                    result.add((T) it.next());
                }
            }
        });
        return result;
    }
}
