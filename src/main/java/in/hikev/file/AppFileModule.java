package in.hikev.file;

import com.google.inject.AbstractModule;
import in.hikev.commons.annotation.AutoloadModule;
import in.hikev.file.repository.AppFileRepository;

/**
 * Created by Administrator on 2015/6/28.
 */
@AutoloadModule
public class AppFileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AppFile.class).to(AppFileRepository.class);
    }
}
