package com.staybooking.staybooking.model.users;

import lombok.*;

import javax.persistence.*;

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
