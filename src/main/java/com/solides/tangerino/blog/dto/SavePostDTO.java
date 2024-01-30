package com.solides.tangerino.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavePostDTO extends CreatePostDTO {

    private long id;

    private String content;

}
