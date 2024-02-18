package com.staybooking.staybooking.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreate {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
