package dev.marvin.business;

import dev.marvin.dbaccess.NotificationRepository;
import dev.marvin.domain.model.Notification;
import dev.marvin.domain.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Async
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendEmail(NotificationRequest notificationRequest) {
        log.info("Inside sendEmail method of NotificationServiceImpl");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationRequest.getTo());
        simpleMailMessage.setSubject(notificationRequest.getSubject());
        simpleMailMessage.setText(notificationRequest.getBody());
        javaMailSender.send(simpleMailMessage);

        Notification notification = Notification.builder()
                .notificationType(notificationRequest.getNotificationType())
                .to(notificationRequest.getTo())
                .subject(notificationRequest.getSubject())
                .body(notificationRequest.getBody())
                .bookingReference(notificationRequest.getBookingReference())
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public void sendSms() {

    }

    @Override
    public void sendWhatsApp() {

    }
}
