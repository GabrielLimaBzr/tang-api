package com.solides.tangerino.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentByPostResponseDTO {

    private Long id;

    private LocalDateTime created;

    private String content;

    private Long postId;

}
