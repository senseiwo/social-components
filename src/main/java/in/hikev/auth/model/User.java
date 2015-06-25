package in.hikev.auth.model;

import in.hikev.commons.hibernate.model.Entity;

/**
 * Created by Administrator on 2015/6/22.
 */
public class User extends Entity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;
    private String password;
    private String email;
}
