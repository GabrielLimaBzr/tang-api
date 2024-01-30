package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.CreatePostDTO;
import com.solides.tangerino.blog.dto.CreatePostResponseDTO;
import com.solides.tangerino.blog.dto.SavePostDTO;
import com.solides.tangerino.blog.dto.SavePostResponseDTO;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.repository.specification.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    CreatePostResponseDTO createPost(CreatePostDTO createPostDTO) throws NotFoundException;

    public Page<Post> getPosts(PostSpecification postSpecification, Pageable pageable);

    SavePostResponseDTO savePost(SavePostDTO savePostDTO) throws NotFoundException;

    Post getById(Long id) throws NotFoundException;
}
