package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.*;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.repository.specification.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    public Page<Post> getPosts(PostSpecification postSpecification, Pageable pageable);

    SavePostResponseDTO savePost(SavePostDTO savePostDTO) throws NotFoundException;

    Post getById(Long id) throws NotFoundException;

    void deletePost(long id) throws NotFoundException, BusinessException;

    List<ResumePostDTO> getAllPostResume();
}
