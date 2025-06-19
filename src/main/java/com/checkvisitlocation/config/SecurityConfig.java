package com.checkvisitlocation.config;

import com.checkvisitlocation.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Конфігурація безпеки для Spring Security.
 * Налаштовує безпеку, аутентифікацію, авторизацію та CORS для додатку.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Налаштовує ланцюжок фільтрів безпеки.
     * Визначає правила безпеки, CORS, авторизацію та сесії.
     * 
     * @param http об'єкт HttpSecurity для налаштування
     * @param jwtAuthFilter фільтр для JWT аутентифікації
     * @return налаштований ланцюжок фільтрів безпеки
     * @throws Exception якщо виникла помилка при налаштуванні
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Створює кодувальник паролів BCrypt.
     * 
     * @return налаштований кодувальник паролів
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Створює менеджер аутентифікації.
     * 
     * @param authenticationConfiguration конфігурація аутентифікації
     * @return налаштований менеджер аутентифікації
     * @throws Exception якщо виникла помилка при створенні менеджера
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Налаштовує конфігурацію CORS.
     * Дозволяє запити з локального сервера розробки.
     * 
     * @return налаштоване джерело конфігурації CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * Створює сервіс для роботи з користувачами.
     * 
     * @param userService сервіс користувачів
     * @return налаштований сервіс користувачів
     */
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService;
    }
}