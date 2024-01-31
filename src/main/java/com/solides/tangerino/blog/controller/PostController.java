package com.solides.tangerino.blog.controller;

import com.solides.tangerino.blog.dto.*;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.File;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.repository.specification.PostSpecification;
import com.solides.tangerino.blog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("post")
@Tag(name = "POST")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

//    @PostMapping("create")
//    @SecurityRequirement(name = "Bearer Authentication")
//    public ResponseEntity<CreatePostResponseDTO> createPost(@RequestBody @Valid CreatePostDTO createPostDTO) throws NotFoundException {
//        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(createPostDTO));
//    }

    @PostMapping("save")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SavePostResponseDTO> savePost(@RequestBody @Valid SavePostDTO savePostDTO) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(savePostDTO));
    }

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SavePostResponseDTO> publish(
            @RequestPart("post") @Valid SavePostDTO savePostDTO,
            @RequestPart("file") MultipartFile[] file) throws NotFoundException, BusinessException {
        try {
            Set<File> files = uploadFile(file);
            savePostDTO.setPostFiles(files);
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(savePostDTO));
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    private Set<File> uploadFile(MultipartFile[] multipartFiles) throws IOException {
        Set<File> fileModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
           File fileModel = new File(
                   file.getOriginalFilename(),
                   file.getContentType(),
                   file.getBytes()
           );
           fileModels.add(fileModel);
        }
        return fileModels;
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getPosts(@RequestParam(required = false) String title,
                                               @PageableDefault(page = 0, size = 10) Pageable pageable)  {
        PostSpecification postSpecification = new PostSpecification();
        postSpecification.setTitle(title);
        return ResponseEntity.ok().body(postService.getPosts(postSpecification, pageable));
    }

    @DeleteMapping("{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity deletePost(@PathVariable long id) throws NotFoundException, BusinessException {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("all-resume-posts")
    public ResponseEntity<List<ResumePostDTO>>  getAllPostResume() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostResume());
    }
}
