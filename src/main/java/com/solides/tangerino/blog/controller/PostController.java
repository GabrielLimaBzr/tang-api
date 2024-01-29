package com.solides.tangerino.blog.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
@Tag(name = "POST")
@AllArgsConstructor
public class PostController {

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok().body("TESTE");
    }
}
