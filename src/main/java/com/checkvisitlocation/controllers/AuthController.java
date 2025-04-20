package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.LoginRequest;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API для реєстрації та автентифікації користувачів")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Реєстрація нового користувача",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Користувач успішно зареєстрований",
                            content = @Content(schema = @Schema(implementation = User.class)))
            }
    )
    public ResponseEntity<User> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Дані для реєстрації",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class)))
            @RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Вхід в систему",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успішна автентифікація",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Невірні облікові дані")
            }
    )
    public ResponseEntity<User> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Облікові дані",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginRequest.class)))
            @RequestBody LoginRequest request) {
        return userService.authenticate(request.getUsername(), request.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}