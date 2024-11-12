package com.koi.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;


    @Column
     Long comment;

    @Column
     Long votes;

    @Column(nullable = false)

     LocalDateTime createDate;

    @Column
     Long price;

    @Column(length = 255)
     String address;

    @Column(length = 255)
     String description;

    @Column(length = 255)
     String imageUrl;

    @Column(length = 255, nullable = false)
     String title;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
