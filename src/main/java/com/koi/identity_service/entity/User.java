package com.koi.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String userName;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
//     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
//    List<Post> posts;


}
