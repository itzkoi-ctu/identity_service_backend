package com.koi.identity_service.service;

import com.koi.identity_service.dto.request.PostCreationRequest;
import com.koi.identity_service.entity.Post;
import com.koi.identity_service.entity.User;
import com.koi.identity_service.mapper.PostMapper;
import com.koi.identity_service.repository.PostRepository;
import com.koi.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
     PostRepository postRepository;
    PostMapper postMapper;
    UserRepository userRepository;


    public Post createPost(PostCreationRequest request){
        System.out.println("Entering createPost method");

        User user= userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Khong tim thay user"));
        System.out.println("user posted: "+user);
        Post post= postMapper.toPost(request);
        System.out.println("Post mapped: "+  post);
        post.setUser(user);
        return postRepository.save(post);
    }
    public List<Post> getPosts(){
        return postRepository.findAll();
    }


}
