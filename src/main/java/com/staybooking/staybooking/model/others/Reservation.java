package com.staybooking.staybooking.model.others;

import com.staybooking.staybooking.model.users.Renter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private double price;
    private boolean canceled;
    @ManyToOne
    private Renter renter;
    @ManyToOne(optional = false)
    private Accommodation accommodation;
}
