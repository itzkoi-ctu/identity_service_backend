package com.koi.identity_service.mapper;


import com.koi.identity_service.dto.request.UserCreationRequest;
import com.koi.identity_service.dto.request.UserUpdateRequest;
import com.koi.identity_service.dto.response.UserResponse1;
import com.koi.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    UserResponse1 toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
