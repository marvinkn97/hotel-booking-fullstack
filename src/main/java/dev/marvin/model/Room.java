package dev.marvin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @SequenceGenerator(name = "room_id_sequence", sequenceName = "room_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_sequence")
    private Long id;
    @Column(unique = true)
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private Integer capacity;
    private String description;
    private String imageUrl;
}
