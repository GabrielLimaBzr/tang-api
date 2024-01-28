package com.solides.tangerino.blog.service.impl;

import com.solides.tangerino.blog.config.security.jwt.JwtTokenService;
import com.solides.tangerino.blog.dto.AuthUserDTO;
import com.solides.tangerino.blog.model.entity.User;
import com.solides.tangerino.blog.repository.UserRepository;
import com.solides.tangerino.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public String authUser(AuthUserDTO authUserDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(authUserDTO.getLogin(), authUserDTO.getPassphrase());
        var login = this.authenticationManager.authenticate(auth);
        return this.jwtTokenService.createJwtToken((User) login.getPrincipal());
    }

}
