package com.oauth_2_spring_security.oauth_2_spring_security.config;


import com.oauth_2_spring_security.oauth_2_spring_security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private  final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers->headers.frameOptions().sameOrigin())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error").permitAll() // allow these without login
                        .anyRequest().authenticated()                         // everything else needs login
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")                // custom login page
                        .defaultSuccessUrl("/home", true)   // always redirect here after success
                        .userInfoEndpoint(userInfo-> userInfo.userService(customOAuth2UserService))
                );

        return http.build();
    }


}
