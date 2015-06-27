package in.hikev.commons.guice.scanner;

import com.google.inject.Module;
import in.hikev.commons.annotation.AutoloadModule;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/27.
 */
public class ModuleScanner {

    private Reflections reflections;
    private InstanceCreator creator;

    private class InstanceCreator{
        public <T> T createInstance(final Class<? extends T> clazz) {
            try {
                Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
                return constructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
            }
        }
    }

    public ModuleScanner(){
        reflections = new Reflections("in.hikev");
        creator =new InstanceCreator();
    }

    public Set<Module> scanForModules() {
        Set<Module> result = new HashSet<Module>();
        for (Class<?> moduleClass : reflections.getTypesAnnotatedWith(AutoloadModule.class)) {
            Module instance = creator.createInstance((Class<? extends Module>) moduleClass);
            result.add(instance);
        }
        return result;
    }
}
