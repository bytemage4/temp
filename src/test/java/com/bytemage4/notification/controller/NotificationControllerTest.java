package com.bytemage4.notification.controller;

import com.bytemage4.notification.model.Notification;
import com.bytemage4.notification.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test for NotificationController using MockMvc with mocked service layer.
 * No database or Spring Boot context required - fast and isolated.
 */
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void createRandomNotification_ShouldReturnCreatedNotification() throws Exception {
        // Given
        Notification notification = new Notification("test-message", Instant.now());
        notification.setMessage("test-message");
        when(notificationService.createRandomNotification()).thenReturn(notification);

        // When & Then
        mockMvc.perform(post("/notification/db"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.message").value("test-message"));
    }

    @Test
    void getAllNotifications_ShouldReturnListOfNotifications() throws Exception {
        // Given
        Notification notification1 = new Notification("message-1", Instant.now());
        Notification notification2 = new Notification("message-2", Instant.now());
        List<Notification> notifications = Arrays.asList(notification1, notification2);
        
        when(notificationService.getAllNotifications()).thenReturn(notifications);

        // When & Then
        mockMvc.perform(get("/notification/db/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].message").value("message-1"))
                .andExpect(jsonPath("$[1].message").value("message-2"));
    }
}

