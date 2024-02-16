package com.staybooking.staybooking.model.others;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressName;
    private double longitude;
    private double latitude;

}
