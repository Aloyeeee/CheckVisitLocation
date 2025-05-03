package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.AnalyticsRequest;
import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.services.VisitAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "API для аналізу відвідувань")
public class VisitAnalyticsController {
    private final VisitAnalyticsService analyticsService;

    public VisitAnalyticsController(VisitAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping
    @Operation(
            summary = "Аналіз відвідувань користувача",
            description = "Аналізує відвідування з можливістю фільтрації за періодом, типом локації, відстанню, сортуванням і генерацією звіту",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Результати аналізу",
                            content = @Content(schema = @Schema(implementation = AnalyticsResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некоректний запит",
                            content = @Content)
            }
    )
    public ResponseEntity<AnalyticsResponse> analyzeVisits(
            @Parameter(hidden = true) @AuthenticationPrincipal User user,
            @Valid @RequestBody AnalyticsRequest request) {
        AnalyticsResponse response = analyticsService.analyzeVisits(user, request);
        return ResponseEntity.ok(response);
    }
}