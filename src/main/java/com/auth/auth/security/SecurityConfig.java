package com.auth.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.auth.auth.security.filter.AuthenticationFilter;
import com.auth.auth.security.filter.JWTAuthorizationFilter;
import com.auth.auth.security.manager.CustomAuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    public SecurityConfig(CustomAuthenticationManager customAuthenticationManager){
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/login");

        http
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> request
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/all").permitAll()
                .anyRequest().authenticated()
            )
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


}
