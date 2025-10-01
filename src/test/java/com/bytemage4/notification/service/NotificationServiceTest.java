package com.bytemage4.notification.service;

import com.bytemage4.notification.dao.NotificationRepository;
import com.bytemage4.notification.model.Notification;
import com.bytemage4.notification.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for NotificationService with mocked repository.
 * Tests business logic in isolation without database.
 */
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void createRandomNotification_ShouldSaveAndReturnNotification() {
        // Given
        Notification savedNotification = new Notification("uuid-message", Instant.now());
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        // When
        Notification result = notificationService.createRandomNotification();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo("uuid-message");
        verify(notificationRepository).save(any(Notification.class));
    }

    @Test
    void getAllNotifications_ShouldReturnAllNotifications() {
        // Given
        Notification notification1 = new Notification("message-1", Instant.now());
        Notification notification2 = new Notification("message-2", Instant.now());
        List<Notification> notifications = Arrays.asList(notification1, notification2);
        
        when(notificationRepository.findAll()).thenReturn(notifications);

        // When
        List<Notification> result = notificationService.getAllNotifications();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getMessage()).isEqualTo("message-1");
        assertThat(result.get(1).getMessage()).isEqualTo("message-2");
        verify(notificationRepository).findAll();
    }
}

