package com.koi.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min= 8, message = "USERNAME_INVALID")
     String userName;
    @Size(min= 6, message = "PASSWORD_INVALID")
     String password;

     String firstName;
     String lastName;
     LocalDate dob;


}
