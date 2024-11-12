package com.koi.identity_service.controller;

import com.koi.identity_service.dto.request.ApiResponse;
import com.koi.identity_service.dto.request.PostCreationRequest;
import com.koi.identity_service.entity.Post;
import com.koi.identity_service.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @PostMapping("/createPost")
    ApiResponse<Post> createPost(@RequestBody  PostCreationRequest request){


        System.out.println("Received request to create post: " + request);

        ApiResponse<Post> apiResponse = new ApiResponse<>();
        apiResponse.setResult(postService.createPost(request));
        return apiResponse;
    }

    @GetMapping()
    List<Post> getPosts(){
        return postService.getPosts();
    }

}
