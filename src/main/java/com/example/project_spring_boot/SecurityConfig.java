package com.example.project_spring_boot;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .logout().disable()
                .sessionManagement().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                //.requestMatchers(HttpMethod.GET,"/user").authenticated()
                .requestMatchers(HttpMethod.GET,"/user", "/item").hasAnyAuthority("SCOPE_read")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
        .withJwkSetUri("https://fungover.org/auth/.well-known/jwks.json")
        .jwsAlgorithm(SignatureAlgorithm.ES256)
        .build();
    }
}
