package com.koi.identity_service.controller;


import com.koi.identity_service.dto.request.ApiResponse;
import com.koi.identity_service.dto.request.CommentCreationRequest;
import com.koi.identity_service.entity.Comment;
import com.koi.identity_service.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    CommentService commentService;

    @PostMapping("/createComment")
    ApiResponse<Comment> createComment(CommentCreationRequest request){
        System.out.println("Received request to create comment: " + request);

        ApiResponse<Comment> apiResponse = new ApiResponse<>();
        apiResponse.setResult(commentService.createComment(request));
        return apiResponse;
    }
}
