package com.wanari.jwt.example.authserver.controller;

import com.wanari.jwt.example.authserver.controller.model.CreateJwtApiRequest;
import com.wanari.jwt.example.authserver.controller.model.CreateJwtApiResponse;
import com.wanari.jwt.example.authserver.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @GetMapping("/jwt")
    public ResponseEntity getJwt(@RequestBody CreateJwtApiRequest request) {
        if("The detonator".equals(request.getUsername())) {
            return ResponseEntity.ok(
                CreateJwtApiResponse.builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .build()
            );
        } else {
            return ResponseEntity.ok("Sorry you are not 'The detonator'");
        }

    }
}
