package com.staybooking.staybooking.model.others;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unavailability_periods")
public class UnavailabilityPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean deleted;
    @ManyToOne(optional = false)
    private Accommodation accommodation;

}
