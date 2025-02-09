package dev.marvin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @SequenceGenerator(name = "payment_id_sequence", sequenceName = "payment_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
    private Long id;
    private String transactionId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String bookingReference;
    private String failureReason;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
