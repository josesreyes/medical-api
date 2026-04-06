package com.jsrdev.medapi.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // physicians
                        .requestMatchers(HttpMethod.POST, "/physicians").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/physicians/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/physicians/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/physicians").hasAnyRole("ADMIN", "USER")
                        // patients
                        .requestMatchers(HttpMethod.POST, "/patients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/patients").hasAnyRole("ADMIN", "USER")
                        // appointments
                        .requestMatchers("/appointments/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
