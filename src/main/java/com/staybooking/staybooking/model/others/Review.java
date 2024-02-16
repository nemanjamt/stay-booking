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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;
    private double rating;
    private LocalDateTime createdDate;
    private boolean deleted;
    @ManyToOne
    private Renter renter;
    @ManyToOne(optional = false)
    private Accommodation accommodation;

}
