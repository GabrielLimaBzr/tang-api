package com.solides.tangerino.blog.controller;

import com.solides.tangerino.blog.dto.CreatePostDTO;
import com.solides.tangerino.blog.dto.CreatePostResponseDTO;
import com.solides.tangerino.blog.dto.SavePostDTO;
import com.solides.tangerino.blog.dto.SavePostResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
@Tag(name = "POST")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CreatePostResponseDTO> createPost(@RequestBody @Valid CreatePostDTO createPostDTO) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(createPostDTO));
    }

    @PostMapping("save")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SavePostResponseDTO> savePost(@RequestBody @Valid SavePostDTO savePostDTO) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(savePostDTO));
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
}
