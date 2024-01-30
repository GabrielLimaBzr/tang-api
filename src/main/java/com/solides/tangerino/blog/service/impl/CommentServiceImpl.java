package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.config.security.jwt.JwtTokenService;
import com.solides.tangerino.blog.dto.CommentByPostResponseDTO;
import com.solides.tangerino.blog.dto.CreateCommentDTO;
import com.solides.tangerino.blog.dto.CreateCommentResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.Comment;
import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.model.mapper.CommentMapper;
import com.solides.tangerino.blog.repository.CommentRepository;
import com.solides.tangerino.blog.service.CommentService;
import com.solides.tangerino.blog.service.PostService;
import com.solides.tangerino.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final CommentMapper commentMapper;

    @Override
    public CreateCommentResponseDTO createComment(CreateCommentDTO createComment) throws NotFoundException {
        Post post = postService.getById(createComment.getPostId());
        User user = userService.getById(createComment.getUserId());

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(createComment.getContent());

        return commentMapper.entityToCreateCommentDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentByPostResponseDTO> listCommentsByPost(Long postId) throws NotFoundException {
        Post post = postService.getById(postId);

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(commentMapper::entityToCommentPostDto)
                .collect(Collectors.toList());
    }

    public Comment getById(Long id) throws NotFoundException{
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comentário não encontrado para o id informado."));
    }
    @Override
    public void deleteComment(Long id) throws BusinessException, NotFoundException{
        Comment comment = getById(id);
        User userCurrent = jwtTokenService.getUserCurrent();
        if(!comment.getUser().getId().equals(userCurrent.getId())){
            throw new BusinessException("Apenas o criador do Comentario pode excluir");
        }
        commentRepository.delete(comment);
    }
}
