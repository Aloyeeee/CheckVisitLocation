package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.VisitRequest;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.services.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visits", description = "API для керування відвідуваннями")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    @Operation(
            summary = "Отримати всі відвідування поточного користувача",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список відвідувань",
                            content = @Content(schema = @Schema(implementation = Visit[].class)))
            }
    )
    public List<Visit> getUserVisits(
            @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return visitService.getUserVisits(user);
    }

    @PostMapping
    @Operation(
            summary = "Додати нове відвідування",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Створене відвідування",
                            content = @Content(schema = @Schema(implementation = Visit.class)))
            }
    )
    public Visit addVisit(
            @Parameter(hidden = true) @AuthenticationPrincipal User user,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Дані для створення відвідування",
                    required = true,
                    content = @Content(schema = @Schema(implementation = VisitRequest.class)))
            @RequestBody VisitRequest request) {
        return visitService.addVisit(user, request.getLocation(), request.toVisit());
    }
}