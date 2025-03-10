package com.example.taskmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.withUsername("admin")
	            .password("{noop}admin") // "{noop}" indica que la contraseña no está encriptada
	            .roles("USER")
	            .build();

	        return new InMemoryUserDetailsManager(user);
	    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permitir todas las peticiones
            .formLogin(login -> login.disable()) // Desactiva formulario de login
            .httpBasic(basic -> basic.disable()); // Desactiva autenticación básica
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
