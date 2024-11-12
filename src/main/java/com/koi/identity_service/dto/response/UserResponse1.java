package com.koi.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse1 {
    String id;
    String userName;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

}
