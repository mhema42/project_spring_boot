package com.example.project_spring_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import com.example.project_spring_boot.security.filter.AuthenticationFilter;
import com.example.project_spring_boot.security.filter.ExceptionHandlerFilter;
import com.example.project_spring_boot.security.filter.JWTAutorizationFilter;
import com.example.project_spring_boot.security.manager.CustomAuthenicationManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    CustomAuthenicationManager customAuthenicationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenicationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
            .csrf().disable()
            .httpBasic().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTRATION_PATH).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAutorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            return http.build();
    }

}
