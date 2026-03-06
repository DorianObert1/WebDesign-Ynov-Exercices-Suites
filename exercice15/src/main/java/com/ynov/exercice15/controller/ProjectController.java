package com.ynov.exercice15.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final Map<String, List<String>> projects = Map.of(
            "alice", List.of("Projet A", "Projet B"),
            "bob", List.of("Projet C", "Projet D")
    );

    @GetMapping
    public Mono<Map<String, List<String>>> getProjects(Authentication authentication) {
        String username = authentication.getName();
        List<String> userProjects = projects.getOrDefault(username, List.of());
        return Mono.just(Map.of("projects", userProjects));
    }
}
