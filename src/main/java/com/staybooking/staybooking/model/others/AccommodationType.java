package com.staybooking.staybooking.model.others;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_types")
public class AccommodationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean deleted;
}
