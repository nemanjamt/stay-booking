package com.staybooking.staybooking.model.users;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Moderators")
public class Moderator extends User{
    public boolean deleted;
}
