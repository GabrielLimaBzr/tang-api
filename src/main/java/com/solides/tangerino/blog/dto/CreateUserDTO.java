package com.solides.tangerino.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @Pattern(regexp = "^[a-z0-9._-]+$", message = "Nome de usuário inválido, deverá conter apenas letras minúsculas, números e alguns caracteres especiais")
    @Schema(description = "Login do usuário", example = "loginuser10")
    @Size(max = 100, message = "Login deverá ter no máximo 100 caracteres")
    private String login;

    @Schema(description = "Nome do usuário, máximo 100 caracteres", example = "Jose Silva")
    @NotEmpty(message = "O campo nome é obrigatório")
    @Size(max = 100, message = "Nome de usuário deverá ter no máximo 100 caracteres")
    private String name;

    @NotEmpty(message = "O campo email é obrigatório")
    @Schema(description = "Email do usuário, máximo 100 caracteres", example = "teste@teste.com")
    @Email(message = "Email inválido")
    private String email;

    private String passphrase;
}
