package in.hikev.commons.guice.injectors;

import com.google.inject.MembersInjector;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/6/27.
 */
public class Log4jMemberInjector<T> implements MembersInjector<T> {

    private final Field field;
    private final Logger logger;

    public Log4jMemberInjector(Field field) {
        this.field = field;
        this.logger = Logger.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    public void injectMembers(T t) {
        try {
            field.set(t, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
