package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.config.security.jwt.JwtTokenService;
import com.solides.tangerino.blog.dto.*;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.model.enums.PostStatus;
import com.solides.tangerino.blog.model.mapper.PostMapper;
import com.solides.tangerino.blog.repository.CommentRepository;
import com.solides.tangerino.blog.repository.PostRepository;
import com.solides.tangerino.blog.repository.specification.PostSpecification;
import com.solides.tangerino.blog.service.PostService;
import com.solides.tangerino.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final JwtTokenService jwtTokenService;
    private final CommentRepository commentRepository;

//    @Override
//    public CreatePostResponseDTO createPost(CreatePostDTO createPostDTO) throws NotFoundException{
//        User user = userService.getById(createPostDTO.getUserId());
//
//        Post post = new Post();
//        post.setUser(user);
//        post.setTitle(createPostDTO.getTitle());
//        post.setStatus(createPostDTO.getStatus());
//
//        Post postCreated = postRepository.save(post);
//
//        return postMapper.entityToCreatePostDto(postCreated);
//    }

    @Override
    public Page<Post> getPosts(PostSpecification postSpecification, Pageable pageable) {
        return postRepository.findAll(postSpecification, pageable);
    }

    @Override
    public SavePostResponseDTO savePost(SavePostDTO savePostDTO) throws NotFoundException {
        User userCurrent = jwtTokenService.getUserCurrent();

        User user = userService.getById(userCurrent.getId());

        Post postSave = postMapper.SavePostDtoToEntity(savePostDTO);
        postSave.setUser(user);
        postSave.setStatus(PostStatus.PUBLISHED);

        return postMapper.entityToSavePostDto(postRepository.save(postSave));
    }

    @Override
    public Post getById(Long id) throws NotFoundException {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post não encontrado com o ID fornecido"));
    }

    @Override
    public void deletePost(long id) throws NotFoundException, BusinessException {
        Post post = getById(id);
        User userCurrent = jwtTokenService.getUserCurrent();
        if(!post.getUser().getId().equals(userCurrent.getId())){
            throw new BusinessException("Apenas o criador do POST pode excluir");
        }

        if (!commentRepository.findByPost(post).isEmpty()) {
            commentRepository.deleteByPost(post.getId());
        }
        postRepository.delete(post);
    }

    @Override
    public List<ResumePostDTO> getAllPostResume() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::postToResumePostDTO)
                .collect(Collectors.toList());
    }
}
