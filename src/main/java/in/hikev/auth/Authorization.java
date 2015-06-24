package in.hikev.auth;

import in.hikev.auth.model.User;
import in.hikev.commons.core.ActionResult;

import java.util.Objects;

/**
 * Created by Administrator on 2015/6/22.
 */
public interface Authorization {
    User getUser(int id);

    User getUser(String property,Object value);

    ActionResult login(String name,String password);

    ActionResult signUp(User user);

    int getTotalUserCount();
}
