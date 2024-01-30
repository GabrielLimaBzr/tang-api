package com.solides.tangerino.blog.controller;

import com.solides.tangerino.blog.dto.CommentByPostResponseDTO;
import com.solides.tangerino.blog.dto.CreateCommentDTO;
import com.solides.tangerino.blog.dto.CreateCommentResponseDTO;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@Tag(name = "Comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CreateCommentResponseDTO> createComment(@RequestBody CreateCommentDTO createCommentDTO) throws NotFoundException {
       return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(createCommentDTO));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentByPostResponseDTO>> listCommentsByPost(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.listCommentsByPost(id));
    }

}
