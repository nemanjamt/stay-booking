package com.staybooking.staybooking.model.others;

import jakarta.persistence.*;
import lombok.*;

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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private boolean deleted;

}
