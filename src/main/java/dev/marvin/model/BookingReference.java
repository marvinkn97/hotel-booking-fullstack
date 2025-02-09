package dev.marvin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookingReference {
    @Id
    @SequenceGenerator(name = "reference_id_sequence", sequenceName = "reference_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_id_sequence")
    private Long id;
    @Column(unique = true, nullable = true)
    private String referenceNumber;
}
