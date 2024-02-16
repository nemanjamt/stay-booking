package com.staybooking.staybooking.model.others;

import com.staybooking.staybooking.model.users.AccommodationPublisher;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodations")
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
    @OneToMany(mappedBy = "accommodation")
    private List<Image> images;
    @OneToMany(mappedBy = "accommodation")
    private List<Reservable> reservables;
    @OneToMany(mappedBy = "accommodation")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "accommodation")
    private List<Review> reviews;
}
