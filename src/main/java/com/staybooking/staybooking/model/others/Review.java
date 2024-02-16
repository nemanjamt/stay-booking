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
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;
    private double rating;
    private LocalDateTime createdDate;
    private boolean deleted;
    @ManyToOne(optional = false)
    private Accommodation accommodation;

}
