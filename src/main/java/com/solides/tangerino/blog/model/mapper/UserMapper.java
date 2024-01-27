package com.solides.tangerino.blog.model.mapper;

import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserDtoToEntity(CreateUserDTO createUserDTO);
}
