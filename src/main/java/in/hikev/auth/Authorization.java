package in.hikev.auth;

import in.hikev.auth.model.User;

import java.util.Objects;

/**
 * Created by Administrator on 2015/6/22.
 */
public interface Authorization {
    User getUser(int id);

    User getUser(String property,Object value);
}
