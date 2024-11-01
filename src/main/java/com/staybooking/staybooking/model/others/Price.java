package com.staybooking.staybooking.model.others;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Accommodation accommodation;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private boolean deleted;

}
