package in.hikev.auth.model;

import in.hikev.commons.hibernate.model.Entity;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2015/6/22.
 */
public class User extends Entity {

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(){

    }

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

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;
}
