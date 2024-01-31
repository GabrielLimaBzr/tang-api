package com.solides.tangerino.blog.model.mapper;

import com.solides.tangerino.blog.dto.ResumePostDTO;
import com.solides.tangerino.blog.dto.SavePostDTO;
import com.solides.tangerino.blog.dto.SavePostResponseDTO;
import com.solides.tangerino.blog.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {

    SavePostResponseDTO entityToSavePostDto(Post post);

    Post SavePostDtoToEntity(SavePostDTO savePostDTO);

    ResumePostDTO postToResumePostDTO(Post post);
}
