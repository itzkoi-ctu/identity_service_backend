package com.koi.identity_service.mapper;

import com.koi.identity_service.dto.request.CommentCreationRequest;
import com.koi.identity_service.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface CommentMapper {

    Comment toComment(CommentCreationRequest request);
}
