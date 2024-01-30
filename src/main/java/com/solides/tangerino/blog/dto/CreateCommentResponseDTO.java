package com.solides.tangerino.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateCommentResponseDTO {

    private Long id;

    private LocalDateTime created;
}
