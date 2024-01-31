package com.solides.tangerino.blog.dto;

import com.solides.tangerino.blog.model.entity.File;
import com.solides.tangerino.blog.model.enums.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SavePostDTO {

    private String content;

    private String title;

    private Set<File> postFiles;
}
