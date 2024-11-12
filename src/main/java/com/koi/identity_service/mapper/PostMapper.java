package com.koi.identity_service.mapper;

import com.koi.identity_service.dto.request.PostCreationRequest;
import com.koi.identity_service.entity.Post;
import com.koi.identity_service.entity.User;
import org.mapstruct.*;



@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreationRequest request);
}
