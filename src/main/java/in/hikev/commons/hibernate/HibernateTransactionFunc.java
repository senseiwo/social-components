package in.hikev.commons.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Administrator on 2015/6/22.
 */
public interface HibernateTransactionFunc {
    Object execute(Session session,Transaction transaction);
}
