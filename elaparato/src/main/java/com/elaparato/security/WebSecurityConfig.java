package com.elaparato.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String VENDEDOR = "vendedor";
    public static final String REPOSITOR = "repositor";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/productos/getall", "/productos/**").hasAnyRole(REPOSITOR, ADMIN)
                .requestMatchers(HttpMethod.GET, "/ventas/getall", "/ventas/**").hasAnyRole(VENDEDOR, ADMIN)
                .requestMatchers(HttpMethod.POST, "/ventas/**").hasAnyRole(VENDEDOR, ADMIN)
                .requestMatchers(HttpMethod.PUT, "/ventas/**").hasAnyRole(VENDEDOR, ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/ventas/**").hasAnyRole(VENDEDOR, ADMIN)
                .requestMatchers(HttpMethod.POST, "/productos/**").hasAnyRole(REPOSITOR, ADMIN)
                .requestMatchers(HttpMethod.PUT, "/productos/**").hasAnyRole(REPOSITOR, ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/productos/**").hasAnyRole(REPOSITOR, ADMIN)
                .requestMatchers(HttpMethod.GET, "/users/all").hasAnyRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/users/username/**").hasAnyRole(ADMIN)
                .anyRequest().authenticated();

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}