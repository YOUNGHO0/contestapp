package com.project.contestapplication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.contestapplication.limiter.config.Bucket4jLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(CsrfConfigurer::disable)
                .addFilterBefore(new Bucket4jLimiter(), SecurityContextPersistenceFilter.class)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/v1/contest").hasRole("USER")
                        .anyRequest().permitAll())
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(new EntryPointUnauthorizedHandler(new ObjectMapper())));


//                .authorizeHttpRequests(authRequests -> authRequests.anyRequest().authenticated());
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests((authorizeRequests) ->
//                        authorizeRequests.anyRequest().permitAll()
//                );



        return http.build();

    }
}
