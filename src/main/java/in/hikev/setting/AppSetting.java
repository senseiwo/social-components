package in.hikev.setting;

import in.hikev.setting.model.Setting;

import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface AppSetting {

    Setting addSetting(int type, String key, String value);

    void updateSetting(int type, String key, String value);

    void delete(int type, String key);

    Setting getSetting(int type, String key);

    List<Setting> getAllSettingsByType(int type);
}
