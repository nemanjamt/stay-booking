package com.staybooking.staybooking.model.users;

import com.staybooking.staybooking.model.others.Reservation;
import com.staybooking.staybooking.model.others.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Renters")
public class Renter extends User{
    private boolean confirmedEmail;
    public boolean blocked;
    @OneToMany(mappedBy = "renter")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "renter")
    private List<Review> reviews;

}
