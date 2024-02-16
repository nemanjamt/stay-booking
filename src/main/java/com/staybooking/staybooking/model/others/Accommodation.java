package com.staybooking.staybooking.model.others;

import com.staybooking.staybooking.model.users.AccommodationPublisher;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int bedNumber;
    private int roomNumber;
    private boolean deleted;
    @ManyToOne
    private AccommodationPublisher publisher;
    @ManyToOne
    private AccommodationType type;
    @OneToOne
    private Location location;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> images;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservable> reservables;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews;
}
