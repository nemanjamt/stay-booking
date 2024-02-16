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
@Table(name = "reservables")
public class Reservable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private boolean deleted;
    @ManyToOne(optional = false)
    private Accommodation accommodation;

}
