package in.hikev.commons.guice.injector;

import com.google.inject.MembersInjector;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/6/29.
 */
public class HibernateValidatorMemberInjector<T> implements MembersInjector<T> {

    private final Field field;
    private final Validator validator;

    public HibernateValidatorMemberInjector(Field field) {
        this.field = field;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        field.setAccessible(true);
    }

    public void injectMembers(T t) {
        try {
            field.set(t, validator);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
