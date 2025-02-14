package dev.marvin.domain.model;

import dev.marvin.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_references")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingReference extends BaseEntity {
    @Id
    @SequenceGenerator(name = "reference_id_sequence", sequenceName = "reference_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_id_sequence")
    private Long id;

    @Column(unique = true, nullable = false)
    private String referenceNumber;
}
