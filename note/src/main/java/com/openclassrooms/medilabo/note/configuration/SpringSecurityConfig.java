package com.openclassrooms.medilabo.note.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests
     *
     * @param http The `HttpSecurity` object used to configure HTTP security settings
     * @return The configured `SecurityFilterChain` instance
     * @throws Exception If an error occurs while configuring HTTP security
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((req) -> req.requestMatchers("/note/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }

}
