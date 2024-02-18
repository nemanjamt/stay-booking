package com.staybooking.staybooking.dto.user.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
}
