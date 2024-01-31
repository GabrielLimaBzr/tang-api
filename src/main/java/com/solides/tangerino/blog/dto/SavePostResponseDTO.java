package com.solides.tangerino.blog.dto;

import com.solides.tangerino.blog.model.enums.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SavePostResponseDTO {

    private Long id;

    private String title;

    private PostStatus status;

    private LocalDateTime created;
}
