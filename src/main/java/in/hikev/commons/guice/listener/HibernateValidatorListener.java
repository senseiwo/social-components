package in.hikev.commons.guice.listener;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.guice.injector.HibernateValidatorMemberInjector;

import javax.validation.Validator;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/6/29.
 */
public class HibernateValidatorListener implements TypeListener {
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Validator.class && field.isAnnotationPresent(HibernateValidator.class)) {
                    typeEncounter.register(new HibernateValidatorMemberInjector<I>(field));
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
