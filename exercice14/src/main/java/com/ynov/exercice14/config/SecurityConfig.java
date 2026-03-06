package com.ynov.exercice14.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        ObjectMapper mapper = new ObjectMapper();

        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.GET, "/api/rooms").authenticated()
                        .pathMatchers(HttpMethod.POST, "/api/rooms").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/api/rooms/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .httpBasic(basic -> {})
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint((exchange, ex) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                            byte[] bytes;
                            try {
                                bytes = mapper.writeValueAsBytes(Map.of(
                                        "error", "UNAUTHORIZED",
                                        "message", "Vous devez vous connecter pour accéder à cette ressource"
                                ));
                            } catch (Exception e) {
                                bytes = new byte[0];
                            }
                            return exchange.getResponse().writeWith(
                                    Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
                            );
                        })
                        .accessDeniedHandler((exchange, ex) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                            byte[] bytes;
                            try {
                                bytes = mapper.writeValueAsBytes(Map.of(
                                        "error", "FORBIDDEN",
                                        "message", "Vous n'avez pas les droits nécessaires pour accéder à cette ressource"
                                ));
                            } catch (Exception e) {
                                bytes = new byte[0];
                            }
                            return exchange.getResponse().writeWith(
                                    Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
                            );
                        })
                )
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        var user = User.withDefaultPasswordEncoder()
                .username("user").password("user").roles("USER").build();
        var admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin").roles("ADMIN").build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}
