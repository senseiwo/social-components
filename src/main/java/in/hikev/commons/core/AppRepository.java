package in.hikev.commons.core;

import in.hikev.commons.hibernate.base.HibernateDaoSupport;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/29.
 */
public class AppRepository extends HibernateDaoSupport {
    protected <T> ActionResult extractValidationErrors(Set<ConstraintViolation<T>> violations){
        ActionResult<T> result = new ActionResult<T>();
        if(violations.size()>0){
            result.setStatusCode(StatusCode.VALIDATION_ERROR);
            Iterator it = violations.iterator();
            while (it.hasNext()){
                ConstraintViolation<T> violation = (ConstraintViolation<T>)it.next();
                String message = String.format("%s : %s %s", violation.getRootBeanClass().getName(),
                        violation.getPropertyPath().toString(),
                        violation.getMessage());
                result.addMessage(message);
            }
            result.setViolations(violations);
            return result;
        }
        result.setStatusCode(StatusCode.DATA_ISVALID);
        return result;
    }
}
