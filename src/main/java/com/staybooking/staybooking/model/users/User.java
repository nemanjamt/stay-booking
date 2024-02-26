package com.staybooking.staybooking.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String phoneNumber;
}
