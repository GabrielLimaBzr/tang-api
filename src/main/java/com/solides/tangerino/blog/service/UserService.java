package com.solides.tangerino.blog.service;

import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.dto.CreateUserResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;

public interface UserService {

    CreateUserResponseDTO createUser(CreateUserDTO createUserDTO) throws BusinessException;

}
