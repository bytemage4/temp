package com.bytemage4.notification.service;

import com.bytemage4.notification.model.Notification;
import java.util.List;

public interface NotificationService {

    Notification createRandomNotification();

    List<Notification> getAllNotifications();
}
