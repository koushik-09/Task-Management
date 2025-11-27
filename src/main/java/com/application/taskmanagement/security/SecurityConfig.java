package com.application.taskmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(configurer ->
//                configurer.anyRequest().authenticated()
//                ).formLogin(form -> form.loginPage("/showLoginPage")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//        )
//                .logout(logout -> logout.permitAll());
//        return http.build();
//    }
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            // Permit the H2 console and everything else requires login
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            )
            // For local dev you can keep form login/basic login
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())

            // Allow H2 console to render inside a frame
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            // Either disable CSRF entirely for dev OR ignore it for the H2 console
            // Option A (simpler for dev):
            .csrf(csrf -> csrf.disable());

    // Option B (safer, only ignore for H2):
    // .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")));

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
