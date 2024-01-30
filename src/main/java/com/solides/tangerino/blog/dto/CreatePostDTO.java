package com.solides.tangerino.blog.dto;

import com.solides.tangerino.blog.model.enums.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDTO {

    private String title;

    private PostStatus status;

    private Long userId;
}
