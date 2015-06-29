package in.hikev.auth.repository;

import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.AppRepository;
import in.hikev.commons.core.StatusCode;
import org.apache.log4j.Logger;

import javax.validation.Validator;

/**
 * Created by Administrator on 2015/6/22.
 */
public class AuthorizationRepository extends AppRepository implements Authorization {

    @Log4jLogger Logger logger;

    @HibernateValidator
    Validator validator;

    public User getUser(final int id) {
        return get(User.class, id);
    }

    public User getUser(String property, Object value) {
        return get(User.class, property, value);
    }

    public ActionResult login(final String name, final String password) {
        User data = querySingle("from User u where u.name = ? and u.password = ?", name, password);
        ActionResult<User> result = new ActionResult<User>(
                data == null ? StatusCode.LOGIN_FAILURE : StatusCode.OK, data
        );
        return result;
    }

    public ActionResult signUp(final User user) {
        ActionResult<User> result = new ActionResult<User>();
        if (exist("from User u where u.name = ? ", user.getName())) {
            result.setStatusCode(StatusCode.SIGNUP_FAILURE_USERNAME_EXIST);
            return result;
        }
        if (exist("from User u where u.email = ? ", user.getEmail())) {
            result.setStatusCode(StatusCode.SIGNUP_FAILURE_EMAIL_EXIST);
            return result;
        }

        result = extractValidationErrors(validator.validate(user));
        if(result.getStatusCode() == StatusCode.VALIDATION_ERROR){
            return result;
        }

        User u = save(user);
        if (u.getId() > 0) {
            result.setData(u);
            result.setStatusCode(StatusCode.OK);
            return result;
        }
        result.setStatusCode(StatusCode.INTERNAL_ERROR);
        return result;
    }

    public int getTotalUserCount() {
        return count("from User");
    }
}
