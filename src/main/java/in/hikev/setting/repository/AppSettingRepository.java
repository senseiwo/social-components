package in.hikev.setting.repository;

import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.StatusCode;
import in.hikev.commons.hibernate.base.HibernateDaoSupport;
import in.hikev.setting.AppSetting;
import in.hikev.setting.model.Setting;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class AppSettingRepository extends HibernateDaoSupport implements AppSetting {
    @Log4jLogger
    Logger logger;

    public ActionResult addSetting(int type, String key, String value){
        ActionResult result = new ActionResult();
        if(keyExists(type,key)){
            result.setStatusCode(StatusCode.SETTING_KEY_EXIST);
            return result;
        }
        Setting setting = new Setting();

        setting.setType(type);
        setting.setKey(key);
        setting.setValue(value);
        setting.setLastUpdateTime(new Date());
        setting = save(setting);

        result.setData(setting);
        result.setStatusCode(StatusCode.OK);
        return result;
    }

    public void updateSetting(int type, String key, String value) {
        Setting setting = querySingle("from Setting s where s.type = ? and s.key = ?", type, key);
        if (setting != null) {
            setting.setValue(value);
            update(setting);
        }
    }

    public void delete(int type, String key) {
        if (keyExists(type, key)) {
            Setting setting = querySingle("from Setting s where s.type = ? and s.key = ?", type, key);
            delete(setting);
        }
    }

    private boolean keyExists(int type,String key) {
        return exist("from Setting s where s.type = ? and s.key = ?", type, key);
    }

    public Setting getSetting(int type, String key){
        return querySingle("from Setting s where s.type = ? and s.key = ?", type, key);
    }

    public List<Setting> getAllSettingsByType(int type) {
        return query("from Setting s where s.type = ?", type);
    }
}
