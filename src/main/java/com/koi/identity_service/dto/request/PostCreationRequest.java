package com.koi.identity_service.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class PostCreationRequest {

     String userId;
     Long comment;


     Long votes;


     LocalDateTime createDate;

     Long price;

     String address;

     String description;

     String imageUrl;

     String title;


}
