package com.bytemage4.notification.service.impl;

import com.bytemage4.notification.dao.NotificationRepository;
import com.bytemage4.notification.model.Notification;
import com.bytemage4.notification.service.NotificationService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public Notification createRandomNotification() {
        Notification notification = new Notification(UUID.randomUUID().toString(), Instant.now());
        return notificationRepository.save(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
