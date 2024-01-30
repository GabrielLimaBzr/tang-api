package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.CommentByPostResponseDTO;
import com.solides.tangerino.blog.dto.CreateCommentDTO;
import com.solides.tangerino.blog.dto.CreateCommentResponseDTO;
import com.solides.tangerino.blog.exceptions.NotFoundException;

import java.util.List;

public interface CommentService {

    CreateCommentResponseDTO createComment(CreateCommentDTO createComment) throws NotFoundException;

    List<CommentByPostResponseDTO> listCommentsByPost(Long postId) throws NotFoundException;
}
