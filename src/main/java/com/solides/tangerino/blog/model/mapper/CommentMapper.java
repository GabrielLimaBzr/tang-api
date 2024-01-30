package com.solides.tangerino.blog.model.mapper;

import com.solides.tangerino.blog.dto.CommentByPostResponseDTO;
import com.solides.tangerino.blog.dto.CreateCommentResponseDTO;
import com.solides.tangerino.blog.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {

    CreateCommentResponseDTO entityToCreateCommentDto(Comment comment);

    @Mapping(source = "post.id", target = "postId")
    CommentByPostResponseDTO entityToCommentPostDto(Comment comment);
}
