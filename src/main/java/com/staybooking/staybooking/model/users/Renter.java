package com.staybooking.staybooking.model.users;

import com.staybooking.staybooking.model.others.Reservation;
import com.staybooking.staybooking.model.others.Review;
import lombok.*;

import javax.persistence.*;
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
    @OneToMany
    private List<Reservation> reservations;
    @OneToMany
    private List<Review> reviews;

}
