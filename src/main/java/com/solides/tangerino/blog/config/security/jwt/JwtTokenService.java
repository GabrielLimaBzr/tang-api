package com.solides.tangerino.blog.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.solides.tangerino.blog.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;
    @Value("${api.security.token.hours_to_expire}")
    private int hoursToExpire;

    public String createJwtToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("tang-api")
                    .withSubject(user.getLogin())
                    .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")))
                    .withExpiresAt(LocalDateTime.now().plusHours(hoursToExpire).toInstant(ZoneOffset.of("-03:00")))
                    .sign(getAlgorithm(secretKey));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o Token JWT", exception);
        }
    }

    public String validateJwtToken(String jwtToken) {
        try {
            return JWT.require(getAlgorithm(secretKey))
                    .withIssuer("tang-api")
                    .build()
                    .verify(jwtToken)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Algorithm getAlgorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }
}
