package in.hikev.notification.model;

import in.hikev.commons.hibernate.model.Entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/1.
 */
public class Notification extends Entity {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public String getSourceObjectModel() {
        return sourceObjectModel;
    }

    public void setSourceObjectModel(String sourceObjectModel) {
        this.sourceObjectModel = sourceObjectModel;
    }

    public int getSourceObjectId() {
        return sourceObjectId;
    }

    public void setSourceObjectId(int sourceObjectId) {
        this.sourceObjectId = sourceObjectId;
    }

    public String getTargetObjectModel() {
        return targetObjectModel;
    }

    public void setTargetObjectModel(String targetObjectModel) {
        this.targetObjectModel = targetObjectModel;
    }

    public int getTargetObjectId() {
        return targetObjectId;
    }

    public void setTargetObjectId(int targetObjectId) {
        this.targetObjectId = targetObjectId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @NotNull
    private int seen;

    @NotNull
    private String sourceObjectModel;

    @NotNull
    private int sourceObjectId;

    @NotNull
    private String targetObjectModel;

    @NotNull
    private int targetObjectId;

    @NotNull
    private Date lastUpdateTime;
}
