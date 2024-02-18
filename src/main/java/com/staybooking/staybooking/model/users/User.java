package com.staybooking.staybooking.model.users;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @SequenceGenerator(name = "SeqGenV1", sequenceName = "SeqV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenV1")
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phoneNumber;
}
