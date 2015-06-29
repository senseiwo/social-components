package in.hikev.setting.model;

import in.hikev.commons.hibernate.model.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/27.
 */
public class Setting extends Entity{
    // Fields

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @NotNull
    private int type;

    @NotNull
    private String key;

    @NotNull
    private String value;
    private Date lastUpdateTime;
}
