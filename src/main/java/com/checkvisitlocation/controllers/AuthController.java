package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.AuthResponse;
import com.checkvisitlocation.dtos.LoginRequest;
import com.checkvisitlocation.dtos.RegisterRequest;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.security.JwtTokenUtil;
import com.checkvisitlocation.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Контролер для обробки запитів автентифікації та реєстрації користувачів.
 * Надає ендпоінти для реєстрації нових користувачів та входу в систему.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Створює новий екземпляр контролера автентифікації.
     * 
     * @param userService сервіс для роботи з користувачами
     * @param jwtTokenUtil утиліта для роботи з JWT токенами
     */
    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Обробляє запит на реєстрацію нового користувача.
     * 
     * @param request дані для реєстрації користувача
     * @return ResponseEntity з зареєстрованим користувачем або повідомленням про помилку
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User registeredUser = userService.register(request);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Обробляє запит на вхід в систему.
     * 
     * @param request дані для входу (логін та пароль)
     * @return ResponseEntity з JWT токеном або повідомленням про помилку автентифікації
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            UserDetails userDetails = userService.authenticate(request.getUsername(), request.getPassword());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (IllegalArgumentException | org.springframework.security.authentication.BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}