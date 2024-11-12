package com.koi.identity_service.controller;

import com.koi.identity_service.dto.request.ApiResponse;
import com.koi.identity_service.dto.request.UserCreationRequest;
import com.koi.identity_service.dto.request.UserUpdateRequest;
import com.koi.identity_service.dto.response.UserResponse1;
import com.koi.identity_service.entity.User;
import com.koi.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/createUser")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;

    }
    @GetMapping()
    List<User> getUsers(){
        return userService.getUser();
    }
    @GetMapping("/{userId}")
    UserResponse1 getUserById(@PathVariable String userId){
    //User getUserById(@PathVariable ("userId)): khai bao cach tuong minh;
        return userService.getUserById(userId);
    }
    @PutMapping("/createUser/{userId}")
    UserResponse1 updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }
    @DeleteMapping("deleteUser/{userId}")
    String deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
        return ("User has been deleted");
    }
}
