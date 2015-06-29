package in.hikev.setting.repository;

import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.AppRepository;
import in.hikev.commons.core.StatusCode;
import in.hikev.file.model.File;
import in.hikev.setting.AppSetting;
import in.hikev.setting.model.Setting;
import org.apache.log4j.Logger;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class AppSettingRepository extends AppRepository implements AppSetting {
    @Log4jLogger
    Logger logger;

    @HibernateValidator
    Validator validator;

    public ActionResult<Setting> addSetting(int type, String key, String value){
        ActionResult<Setting> result = new ActionResult<Setting>();
        if(keyExists(type,key)){
            result.setStatusCode(StatusCode.SETTING_KEY_EXIST);
            return result;
        }
        Setting setting = new Setting();

        setting.setType(type);
        setting.setKey(key);
        setting.setValue(value);
        setting.setLastUpdateTime(new Date());

        result = extractValidationErrors(validator.validate(setting));
        if(result.getStatusCode() == StatusCode.VALIDATION_ERROR){
            return result;
        }
        setting = save(setting);
        result.setData(setting);
        result.setStatusCode(StatusCode.OK);
        return result;
    }

    public void updateSetting(int type, String key, String value) {
        Setting setting = querySingle("from Setting s where s.type = ? and s.key = ?", type, key);
        if (setting != null && value != null) {
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
