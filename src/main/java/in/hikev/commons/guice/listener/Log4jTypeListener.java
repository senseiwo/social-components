package in.hikev.commons.guice.listener;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.guice.injector.Log4jMemberInjector;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/6/27.
 */
public class Log4jTypeListener implements TypeListener {
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Logger.class &&
                        field.isAnnotationPresent(Log4jLogger.class)) {
                    typeEncounter.register(new Log4jMemberInjector<I>(field));
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
