package com.staybooking.staybooking.model.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Administrators")
public class Administrator extends User{

}
