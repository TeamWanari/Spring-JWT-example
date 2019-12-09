package com.wanari.jwt.example.resourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @GetMapping("/secret")
    public ResponseEntity<String> getSecret() {
        return ResponseEntity.ok("SH! This is a secret! You can detonate Printers!");
    }

}
