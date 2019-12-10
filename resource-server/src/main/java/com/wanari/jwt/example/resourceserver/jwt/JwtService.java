package com.wanari.jwt.example.resourceserver.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    private final JwtKeyProvider jwtKeyProvider;

    boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtKeyProvider.getPublicKey()).parseClaimsJws(jwt);
            return true;
        } catch(JwtException e) {
            log.warn("Invalid JWT!", e);
        }
        return false;
    }

    public String getUsernameFrom(String jwt) {
        return (String) getClaims(jwt).get("username");
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
            .setSigningKey(jwtKeyProvider.getPublicKey())
            .parseClaimsJws(jwt)
            .getBody();
    }
}
