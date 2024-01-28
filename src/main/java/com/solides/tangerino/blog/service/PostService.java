package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.CreatePostDTO;
import com.solides.tangerino.blog.dto.CreatePostResponseDTO;

public interface PostService {

    CreatePostResponseDTO createPost(CreatePostDTO createPostDTO);

}
