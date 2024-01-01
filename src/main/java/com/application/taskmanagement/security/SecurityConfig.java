package com.application.taskmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer.anyRequest().authenticated()
                ).formLogin(form -> form.loginPage("/showLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
        )
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("admin")
                .password("{noop}admin")
                .build();

        return new InMemoryUserDetailsManager(john);
    }
}
