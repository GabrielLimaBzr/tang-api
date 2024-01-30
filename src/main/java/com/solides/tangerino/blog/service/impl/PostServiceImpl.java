package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.dto.CreatePostDTO;
import com.solides.tangerino.blog.dto.CreatePostResponseDTO;
import com.solides.tangerino.blog.dto.SavePostDTO;
import com.solides.tangerino.blog.dto.SavePostResponseDTO;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.model.mapper.PostMapper;
import com.solides.tangerino.blog.repository.PostRepository;
import com.solides.tangerino.blog.repository.specification.PostSpecification;
import com.solides.tangerino.blog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.solides.tangerino.blog.repository.UserRepository;
import com.solides.tangerino.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public CreatePostResponseDTO createPost(CreatePostDTO createPostDTO) throws NotFoundException{
        User user = userService.getById(createPostDTO.getUserId());

        Post post = new Post();
        post.setUser(user);
        post.setTitle(createPostDTO.getTitle());
        post.setStatus(createPostDTO.getStatus());

        Post postCreated = postRepository.save(post);

        return postMapper.entityToCreatePostDto(postCreated);
    }

    @Override
    public Page<Post> getPosts(PostSpecification postSpecification, Pageable pageable) {
        return postRepository.findAll(postSpecification, pageable);
    }

    @Override
    public SavePostResponseDTO savePost(SavePostDTO savePostDTO) throws NotFoundException {
        Post postCreate = getById(savePostDTO.getId());

        Post postSave = postMapper.SavePostDtoToEntity(savePostDTO);
        postSave.setId(postCreate.getId());
        postSave.setCreated(LocalDateTime.now());

        return postMapper.entityToSavePostDto(postRepository.save(postSave));
    }

    @Override
    public Post getById(Long id) throws NotFoundException {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post não encontrado com o ID fornecido"));
    }
}
