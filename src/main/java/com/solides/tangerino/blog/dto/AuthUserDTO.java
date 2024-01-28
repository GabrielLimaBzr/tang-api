package com.solides.tangerino.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    @NotEmpty(message = "O campo login é obrigatório")
    @Schema(description = "Login do usuário", example = "teste")
    private String login;

    @NotEmpty(message = "O campo senha é obrigatório")
    @Schema(description = "Senha do usuário", example = "teste")
    private String passphrase;
}
