package in.hikev.commons.annotation;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2015/6/29.
 */
@Scope
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface HibernateValidator {
}
