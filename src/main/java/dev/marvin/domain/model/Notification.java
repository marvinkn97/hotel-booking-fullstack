package dev.marvin.domain.model;

import dev.marvin.domain.common.BaseEntity;
import dev.marvin.domain.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notification extends BaseEntity {
    @Id
    @SequenceGenerator(name = "notification_id_sequence", sequenceName = "notification_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
    @Column(name = "notification_id")
    private Long id;

    private String subject;
    private String to;
    private String body;

    @Column(name = "booking_reference_number")
    private String bookingReference;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
