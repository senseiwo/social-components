package in.hikev.commons;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import in.hikev.commons.annotation.AutoloadModule;
import in.hikev.commons.guice.listener.Log4jTypeListener;

/**
 * Created by Administrator on 2015/6/26.
 */
@AutoloadModule
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bindListener(Matchers.any(), new Log4jTypeListener());
    }
}
