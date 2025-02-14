package dev.marvin.business;

import dev.marvin.domain.request.NotificationRequest;

public interface NotificationService {
    void sendEmail(NotificationRequest notificationRequest);
    void sendSms();
    void sendWhatsApp();
}
