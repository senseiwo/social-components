package in.hikev.auth.repository;

import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.hibernate.base.HibernateSupport;

/**
 * Created by Administrator on 2015/6/22.
 */
public class AuthorizationRepository extends HibernateSupport implements Authorization {

    public User getUser(final int id) {
        return (User)get(User.class, id);
    }

    public User getUser(String property, Object value) {
        return (User)get(User.class.getSimpleName(), property, value);
    }
}
