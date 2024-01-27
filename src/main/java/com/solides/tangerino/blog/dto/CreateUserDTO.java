package com.solides.tangerino.blog.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @Pattern(regexp = "^[a-z0-9._-]+$", message = "Nome de usuário inválido, deverá conter apenas letras minúsculas, números e alguns caracteres especiais")
    @Size(max = 100, message = "Login deverá ter no máximo 100 caracteres")
    private String login;

    @NotEmpty(message = "O campo nome é obrigatório")
    @Size(max = 100, message = "Nome de usuário deverá ter no máximo 100 caracteres")
    private String name;

    @NotEmpty(message = "O campo email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private String passpharase;
}
