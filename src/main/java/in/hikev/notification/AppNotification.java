package in.hikev.notification;

import in.hikev.commons.core.ActionResult;
import in.hikev.notification.model.Notification;

import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
public interface AppNotification {

    ActionResult<Notification> notifyUser(int userId, String sourceModel, int sourceId, String targetModel, int targetId);

    void removeNotification(int id);

    void setSeen(int id);

    void clearNotifications(int userId);

    List<Notification> getAllNotifications(int startIndex,int maxResult,int userId);
}
