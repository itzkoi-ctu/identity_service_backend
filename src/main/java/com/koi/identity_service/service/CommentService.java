package com.koi.identity_service.service;


import com.koi.identity_service.dto.request.CommentCreationRequest;
import com.koi.identity_service.entity.Comment;
import com.koi.identity_service.entity.Post;
import com.koi.identity_service.entity.User;
import com.koi.identity_service.mapper.CommentMapper;
import com.koi.identity_service.mapper.UserMapper;
import com.koi.identity_service.repository.CommentRepository;
import com.koi.identity_service.repository.PostRepository;
import com.koi.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;
    UserRepository userRepository;
    PostRepository postRepository;
    public Comment createComment(CommentCreationRequest request){

       Comment comment= commentMapper.toComment(request);
       System.out.println("Comment Mapped: "+ comment);
       Post post= postRepository.findById(request.getPostId()).orElseThrow(()-> new RuntimeException("Khong tim thay post"));
       comment.setPost(post);
       return commentRepository.save(comment);
    }

}
