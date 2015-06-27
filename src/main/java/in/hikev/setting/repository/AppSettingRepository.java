package in.hikev.setting.repository;

import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.hibernate.base.HibernateDaoSupport;
import in.hikev.setting.AppSetting;
import in.hikev.setting.model.Setting;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/27.
 */
public class AppSettingRepository extends HibernateDaoSupport implements AppSetting {
    @Log4jLogger
    Logger logger;

    public Setting addSetting(@NotNull int type, @NotNull String key, @NotNull String value) {
        Setting setting = new Setting();

        setting.setType(type);
        setting.setKey(key);
        setting.setValue(value);
        setting.setLastUpdateTime(new Date());

        return save(setting);
    }
}
