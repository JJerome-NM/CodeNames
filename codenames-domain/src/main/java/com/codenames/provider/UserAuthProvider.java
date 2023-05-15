package com.codenames.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codenames.entity.UserEntity;
import com.codenames.properties.SecurityProperties;
import com.codenames.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class UserAuthProvider {

    private static final int TOKEN_LIFETIME = 60 * 60 * 60 * 24 * 30;

    private SecurityProperties securityProperties;

    private String secretKey;

    private final UserService userService;

    UserAuthProvider(UserService userService,
                     SecurityProperties securityProperties) {
        this.secretKey = securityProperties.getJwt();

        if (this.secretKey == null){
            this.secretKey = "secret-key";
        }

        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        this.userService = userService;
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_LIFETIME);

        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        UserEntity userEntity = userService.findByNickname(UserEntity.class, decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(userEntity, null, Collections.emptyList());
    }

    public Optional<UserEntity> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return Optional.of(userService.findByNickname(UserEntity.class, decodedJWT.getIssuer()));
    }
}
