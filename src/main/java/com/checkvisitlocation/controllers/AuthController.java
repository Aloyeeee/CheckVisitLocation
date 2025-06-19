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
 * Контролер для аутентифікації та реєстрації користувачів.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Конструктор AuthController.
     * @param userService сервіс користувачів
     * @param jwtTokenUtil утиліта для роботи з JWT токенами
     */
    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Реєстрація нового користувача.
     * @param request запит на реєстрацію
     * @return створений користувач або повідомлення про помилку
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
     * Аутентифікація користувача (вхід).
     * @param request запит на вхід
     * @return JWT токен або повідомлення про помилку
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