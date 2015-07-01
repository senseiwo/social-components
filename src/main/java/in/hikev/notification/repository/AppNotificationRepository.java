package in.hikev.notification.repository;

import com.google.inject.Inject;
import in.hikev.auth.Authorization;
import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.AppRepository;
import in.hikev.commons.core.StatusCode;
import in.hikev.notification.AppNotification;
import in.hikev.notification.model.Notification;
import org.apache.log4j.Logger;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
public class AppNotificationRepository extends AppRepository implements AppNotification {

    @Log4jLogger
    Logger logger;

    @HibernateValidator
    Validator validator;

    @Inject
    Authorization auth;

    public ActionResult<Notification> notifyUser(int userId,String sourceModel,int sourceId,String targetModel,int targetId) {
        ActionResult<Notification> result = new ActionResult<Notification>();
        if (auth.userExist(userId) && !isNotified(userId, sourceModel, sourceId, targetModel, targetId)) {
            Notification noti = new Notification();
            noti.setLastUpdateTime(new Date());
            noti.setUserId(userId);
            noti.setSeen(0);
            noti.setSourceObjectModel(sourceModel);
            noti.setSourceObjectId(sourceId);
            noti.setTargetObjectModel(targetModel);
            noti.setTargetObjectId(targetId);

            result = extractValidationErrors(validator.validate(noti));
            if (result.getStatusCode() == StatusCode.VALIDATION_ERROR) {
                return result;
            }
            noti = save(noti);
            result.setStatusCode(StatusCode.OK);
            result.setData(noti);
            return result;
        }
        result.setStatusCode(StatusCode.NOTIFY_USER_FAILED);
        return result;
    }

    public void removeNotification(int id) {
        if (exist(Notification.class, id)) {
            delete(Notification.class, id);
        }
    }

    public void setSeen(int id) {
        Notification noti = get(Notification.class, id);
        if (noti != null) {
            noti.setSeen(1);
            update(noti);
        }
    }

    public void clearNotifications(int userId) {
        if(auth.userExist(userId)){
            delete("delete Notification n where n.userId = ?", userId);
        }
    }

    public List<Notification> getAllNotifications(int startIndex,int maxResult,int userId) {
        return query(startIndex, maxResult, "from Notification noti where noti.userId = ?", userId);
    }

    private boolean isNotified(int userId,String sourceModel,int sourceId,String targetModel,int targetId) {
        String hql = new StringBuilder()
                .append("from Notification noti where ")
                .append("noti.userId = ? and ")
                .append("noti.sourceObjectModel = ? and ")
                .append("noti.sourceObjectId = ? and ")
                .append("noti.targetObjectModel = ? and ")
                .append("noti.targetObjectId = ?")
                .toString();
        return exist(hql, userId, sourceModel, sourceId, targetModel, targetId);
    }
}
