package com.dean.glot_spot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/index.html", "/style.css", "/script.js", "/api/users/register", "/api/users/validate-magic-link", "/api/users/test").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for testing purposes only

        return http.build();
    }
}
