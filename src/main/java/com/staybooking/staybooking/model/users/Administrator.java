package com.staybooking.staybooking.model.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Administrators")
public class Administrator extends User{

}
