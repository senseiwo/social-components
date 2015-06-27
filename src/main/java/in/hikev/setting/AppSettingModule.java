package in.hikev.setting;

import com.google.inject.AbstractModule;
import in.hikev.commons.annotation.AutoloadModule;
import in.hikev.setting.repository.AppSettingRepository;

/**
 * Created by Administrator on 2015/6/27.
 */
@AutoloadModule
public class AppSettingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AppSetting.class).to(AppSettingRepository.class);
    }
}
