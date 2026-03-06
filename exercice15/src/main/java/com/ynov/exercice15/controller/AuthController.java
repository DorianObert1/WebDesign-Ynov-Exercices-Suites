package com.ynov.exercice15.controller;

import com.ynov.exercice15.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    private final Map<String, String> users = Map.of(
            "alice", "password123",
            "bob", "secret456"
    );

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            return Mono.just(Map.of("token", jwtUtil.generateToken(username)));
        }

        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
    }
}
