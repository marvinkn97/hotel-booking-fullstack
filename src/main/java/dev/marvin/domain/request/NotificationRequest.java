package dev.marvin.domain.request;

import dev.marvin.domain.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequest {
    private NotificationType notificationType;
    private String to;
    private String subject;
    private String body;
    private String bookingReference;
}
