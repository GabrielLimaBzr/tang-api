package com.solides.tangerino.blog.model.mapper;

import com.solides.tangerino.blog.dto.CreatePostResponseDTO;
import com.solides.tangerino.blog.dto.SavePostDTO;
import com.solides.tangerino.blog.dto.SavePostResponseDTO;
import com.solides.tangerino.blog.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {

    CreatePostResponseDTO entityToCreatePostDto(Post post);

    SavePostResponseDTO entityToSavePostDto(Post post);
    @Mapping(target = "user.id", source = "savePostDTO.userId")
    Post SavePostDtoToEntity(SavePostDTO savePostDTO);
}
