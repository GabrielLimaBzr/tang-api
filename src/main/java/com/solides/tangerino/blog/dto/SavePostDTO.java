package com.solides.tangerino.blog.dto;

import com.solides.tangerino.blog.model.entity.File;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SavePostDTO extends CreatePostDTO {

    @NotNull(message = "Id não pode ser nulo")
    private long id;

    private String content;

    private Set<File> postFiles;
}
