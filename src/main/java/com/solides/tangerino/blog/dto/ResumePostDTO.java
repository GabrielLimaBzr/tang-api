package com.solides.tangerino.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResumePostDTO {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime created;
}
