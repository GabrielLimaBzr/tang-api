package com.solides.tangerino.blog.controller;

import com.solides.tangerino.blog.dto.AuthUserDTO;
import com.solides.tangerino.blog.dto.CreateUserDTO;
import com.solides.tangerino.blog.dto.CreateUserResponseDTO;
import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.service.UserService;
import com.solides.tangerino.blog.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@Tag(name = "USER")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthServiceImpl authService;

    @PostMapping("create")
    @Operation(summary = "Create a new user")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("auth")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid AuthUserDTO authUserDTO) {
        return ResponseEntity.ok().body(authService.authUser(authUserDTO));
    }

}
