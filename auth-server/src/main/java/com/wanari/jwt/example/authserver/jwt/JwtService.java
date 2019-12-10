package com.wanari.jwt.example.authserver.jwt;

import com.wanari.jwt.example.authserver.config.JwtConfig;
import com.wanari.jwt.example.authserver.util.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;
    private final JwtKeyProvider jwtKeyProvider;
    private final DateUtil dateUtil;

    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusMinutes(jwtConfig.getExpirationInMinutes());

        return Jwts.builder()
            .setExpiration(dateUtil.toDate(expiryDate))
            .signWith(SignatureAlgorithm.RS256, jwtKeyProvider.getPrivateKey())
            .claim("username", username)
            .compact();
    }
}
