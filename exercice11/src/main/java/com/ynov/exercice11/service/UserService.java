package com.ynov.exercice11.service;

import com.ynov.exercice11.model.User;
import com.ynov.exercice11.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Long id) {
        // on renvoie une erreur si l'utilisateur n'existe pas
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found: " + id)));
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Long id, User updated) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found: " + id)))
                .flatMap(existing -> {
                    existing.setName(updated.getName());
                    existing.setEmail(updated.getEmail());
                    existing.setActive(updated.isActive());
                    return userRepository.save(existing);
                });
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
