package in.hikev.commons.core;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/23.
 */
public class ActionResult<T> {
    private int statusCode;
    private T data;
    private ArrayList<String> messages;
    private Set<ConstraintViolation<T>> violations;

    public ActionResult(int statusCode,T data){
        this();

        this.statusCode = statusCode;
        this.data = data;
    }

    public ActionResult(){
        messages = new ArrayList<String>();
        violations = new HashSet<ConstraintViolation<T>>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String[] getMessages() {
        if (messages.size() == 0) {
            addMessage(StatusCode.getStatusInfo(statusCode));
        }
        return (String[]) messages.toArray();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Set<ConstraintViolation<T>> getViolations() {
        return violations;
    }

    public void setViolations(Set<ConstraintViolation<T>> violations) {
        this.violations = violations;
    }

}
