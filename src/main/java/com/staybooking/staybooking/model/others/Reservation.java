package com.staybooking.staybooking.model.others;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private double price;
    private boolean canceled;
    @ManyToOne(optional = false)
    private Accommodation accommodation;
}
