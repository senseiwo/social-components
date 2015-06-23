package in.hikev.auth.repository;

import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.StatusCode;
import in.hikev.commons.hibernate.HibernateSessionFunc;
import in.hikev.commons.hibernate.HibernateTransactionFunc;
import in.hikev.commons.hibernate.base.HibernateSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public ActionResult login(final String name, final String password) {

        Object data = openSession(new HibernateSessionFunc() {
            public Object execute(Session session) {
                return session.createQuery("from User u where u.name = ? and u.password = ?")
                              .setParameter(0, name)
                              .setParameter(1, password)
                              .uniqueResult();
            }
        });

        ActionResult result= new ActionResult(
                data==null? StatusCode.LOGIN_FAILURE:StatusCode.OK,
                data
        );

        return result;
    }

    public ActionResult signUp(final User user){
        Object data = openSession(new HibernateTransactionFunc() {
            public Object execute(Session session, Transaction transaction) {
                ActionResult result = new ActionResult();

                //todo


                return null;
            }
        });

        ActionResult result= new ActionResult(
                data==null? StatusCode.LOGIN_FAILURE:StatusCode.OK,
                data
        );

        return result;
    }
}
