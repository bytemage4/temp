package com.bytemage4.notification.controller;

import com.bytemage4.notification.model.Notification;
import com.bytemage4.notification.service.NotificationService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/db")
    public ResponseEntity<Notification> createRandomNotification() {
        Notification notification = notificationService.createRandomNotification();
        return ResponseEntity
                .created(URI.create("/notification/db/" + notification.getId()))
                .body(notification);
    }

    @GetMapping("/db")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
}
