package com.solides.tangerino.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {

    private String content;

    private Long postId;

    private Long userId;
}
