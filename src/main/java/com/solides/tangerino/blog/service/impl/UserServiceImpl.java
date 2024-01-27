package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.dto.CreateUserResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.model.mapper.UserMapper;
import com.solides.tangerino.blog.repository.UserRepository;
import com.solides.tangerino.blog.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public CreateUserResponseDTO createUser(CreateUserDTO createUserDTO) throws BusinessException{
        if(userRepository.findByLogin(createUserDTO.getLogin()) != null) {
            throw new BusinessException("Usuario já existente.");
        }

        CreateUserResponseDTO result = new CreateUserResponseDTO();
        User entity = userMapper.createUserDtoToEntity(createUserDTO);
        result.setLogin(userRepository.save(entity).getLogin());

        return result;
    }
}
