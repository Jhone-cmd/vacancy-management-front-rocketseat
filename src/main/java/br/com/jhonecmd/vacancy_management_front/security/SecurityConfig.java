package br.com.jhonecmd.vacancy_management_front.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/candidate/login").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(form -> form.loginPage("/candidate/login"));
        return http.build();
    }
}
