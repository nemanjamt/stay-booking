package com.staybooking.staybooking.model.users;

import com.staybooking.staybooking.model.others.Accommodation;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AccommodationPublishers")
public class AccommodationPublisher extends User{

    private boolean confirmedEmail;
    private boolean blocked;
    @OneToMany
    private List<Accommodation> accommodations;
}
