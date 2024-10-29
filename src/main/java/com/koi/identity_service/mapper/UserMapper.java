package com.koi.identity_service.mapper;


import com.koi.identity_service.dto.request.UserCreationRequest;
import com.koi.identity_service.dto.request.UserUpdateRequest;
import com.koi.identity_service.dto.response.UserResponse;
import com.koi.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "userName", target = "userName")
//    @Mapping(source = "password", target = "password")
//    @Mapping(source = "firstName", target = "firstName")
//    @Mapping(source = "lastName", target = "lastName")
//    @Mapping(source = "dob", target = "dob")
    User toUser(UserCreationRequest request);

    //@Mapping(target = "firstName", ignore = true)
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
