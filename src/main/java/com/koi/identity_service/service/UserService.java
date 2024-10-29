package com.koi.identity_service.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.koi.identity_service.dto.request.UserCreationRequest;
import com.koi.identity_service.dto.request.UserUpdateRequest;
import com.koi.identity_service.dto.response.UserResponse;
import com.koi.identity_service.entity.User;
import com.koi.identity_service.exception.AppException;
import com.koi.identity_service.exception.ErorrCode;
import com.koi.identity_service.mapper.UserMapper;
import com.koi.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;
    public User createUser(UserCreationRequest request){

        if(userRepository.existsByuserName(request.getUserName()))
            throw new AppException(ErorrCode.USER_EXISTED);


        User user= userMapper.toUser(request);
        System.out.println("user mapping: "+ user);

        return userRepository.save(user);

    }
    public List<User> getUser(){
        return userRepository.findAll();
    }
    public UserResponse getUserById(String userId){
       User user = userRepository.findById(userId)
               .orElseThrow(()-> new RuntimeException("User not found"));
       System.out.println("User from repo"+ user);
       UserResponse userResponse= userMapper.toUserResponse(user);

       System.out.println("User return: "+ userResponse);
       return userResponse;

    }
    public UserResponse updateUser(String userId,UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("user not found"));

        userMapper.updateUser(user,request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);

    }
}
