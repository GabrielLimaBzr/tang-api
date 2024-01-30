package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.dto.CreateUserResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import com.solides.tangerino.blog.model.entity.User;

public interface UserService {

    CreateUserResponseDTO createUser(CreateUserDTO createUserDTO) throws BusinessException;

    User getById(Long userId) throws NotFoundException;
}
