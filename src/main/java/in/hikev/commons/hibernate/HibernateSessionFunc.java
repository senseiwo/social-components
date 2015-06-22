package in.hikev.commons.hibernate;

import org.hibernate.Session;

/**
 * Created by Administrator on 2015/6/22.
 */
public interface HibernateSessionFunc {
    Object execute(Session session);
}
