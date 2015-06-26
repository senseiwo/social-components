package in.hikev.commons.annotations;

import javax.inject.Scope;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2015/6/26.
 */
@Scope
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Log4jLogger {}
