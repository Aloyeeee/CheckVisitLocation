package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.VisitResponse;
import com.checkvisitlocation.dtos.VisitRequest;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.services.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Контролер для управління відвідуваннями користувачів.
 * Надає API для створення та отримання відвідувань з різними параметрами фільтрації.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visits", description = "API для керування відвідуваннями")
public class VisitController {
    private final VisitService visitService;

    /**
     * Створює новий екземпляр контролера відвідувань.
     * 
     * @param visitService сервіс для роботи з відвідуваннями
     */
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    /**
     * Отримує список всіх відвідувань авторизованого користувача.
     * 
     * @param user поточний авторизований користувач
     * @return список відвідувань користувача
     */
    @Operation(summary = "Get all visits for the authenticated user", description = "Returns a list of visits for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved visits"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<VisitResponse> getUserVisits(@AuthenticationPrincipal User user) {
        return visitService.getUserVisits(user);
    }

    /**
     * Отримує список відвідувань користувача, відфільтрованих за типами локацій.
     * 
     * @param user поточний авторизований користувач
     * @param locationTypes список типів локацій для фільтрації
     * @return список відфільтрованих відвідувань
     */
    @Operation(summary = "Get visits by location types for the authenticated user",
            description = "Returns a list of visits filtered by specified location types for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered visits"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Invalid location types"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-location-type")
    public List<VisitResponse> getUserVisitsByLocationType(
            @AuthenticationPrincipal User user,
            @RequestParam("locationTypes") List<LocationType> locationTypes) {
        return visitService.getUserVisitsByLocationType(user, locationTypes);
    }

    /**
     * Отримує список відвідувань користувача, відфільтрованих за рейтингом.
     * 
     * @param user поточний авторизований користувач
     * @param rating рейтинг для фільтрації (від 1 до 5)
     * @return список відфільтрованих відвідувань
     */
    @Operation(summary = "Get visits by rating for the authenticated user",
            description = "Returns a list of visits filtered by specified rating for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered visits"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Invalid rating"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-rating")
    public List<VisitResponse> getUserVisitsByRating(
            @AuthenticationPrincipal User user,
            @RequestParam("rating") @Parameter(description = "Rating from 1 to 5") Integer rating) {
        return visitService.getUserVisitsByRating(user, rating);
    }

    /**
     * Створює нове відвідування для авторизованого користувача.
     * 
     * @param user поточний авторизований користувач
     * @param visitRequest дані для створення відвідування
     * @return ResponseEntity з ID створеного відвідування
     */
    @Operation(summary = "Create a new visit for the authenticated user",
            description = "Creates a new visit for the authenticated user with provided location ID, impressions, rating, and visit date, returning the visit ID in JSON format")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Visit created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"id\": 2}"))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Map<String, Long>> createVisit(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody VisitRequest visitRequest) {
        Long visitId = visitService.createVisit(visitRequest, user);
        return ResponseEntity.ok(Map.of("id", visitId));
    }
}