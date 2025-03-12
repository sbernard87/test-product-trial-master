package com.group.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

        // TODO close endpoints if not authorized, except url kind of /actuator,..
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.requestMatchers("/*").permitAll());

        // Add your custom token filter here
        // http.addFilterBefore(new JwtAuthenticationFilter(),
        // UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
