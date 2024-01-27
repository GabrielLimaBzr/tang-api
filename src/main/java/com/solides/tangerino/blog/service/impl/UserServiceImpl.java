package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.dto.CreateUserResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.model.enums.UserRole;
import com.solides.tangerino.blog.model.mapper.UserMapper;
import com.solides.tangerino.blog.repository.UserRepository;
import com.solides.tangerino.blog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public CreateUserResponseDTO createUser(CreateUserDTO createUserDTO) throws BusinessException{
        if(userRepository.findByLoginOrEmail(createUserDTO.getLogin(), createUserDTO.getEmail()) != null) {
            throw new BusinessException("Login ou email existente");
        }

        CreateUserResponseDTO result = new CreateUserResponseDTO();
        User entity = userMapper.createUserDtoToEntity(createUserDTO);

        entity.setPassphrase(passwordEncoder.encode(entity.getPassphrase()));
        entity.setRole(UserRole.DEFAULT);

        System.out.println(entity);

        result.setLogin(userRepository.save(entity).getLogin());

        return result;
    }
}
