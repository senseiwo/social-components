package in.hikev.notification;

import com.google.inject.AbstractModule;
import in.hikev.commons.annotation.AutoloadModule;
import in.hikev.notification.repository.AppNotificationRepository;

/**
 * Created by Administrator on 2015/7/1.
 */
@AutoloadModule
public class NotificationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AppNotification.class).to(AppNotificationRepository.class);
    }
}
