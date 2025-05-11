package com.checkvisitlocation.controllers;

import com.checkvisitlocation.dtos.LocationWithVisitCountResponse;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Locations", description = "API для управління локаціями та тегами")
public class TagController {
    private final LocationService locationService;

    public TagController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Отримати локації за тегами",
            description = "Повертає список локацій, відфільтрованих за тегами, з кількістю відвідувань")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішно отримано локації",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationWithVisitCountResponse.class))),
            @ApiResponse(responseCode = "400", description = "Невалідні теги"),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера")
    })
    @GetMapping("/by-tags")
    public ResponseEntity<List<LocationWithVisitCountResponse>> getLocationsByTags(
            @RequestParam(required = false) @Parameter(description = "Список тегів для фільтрації") List<TagType> tags,
            @RequestHeader(value = "Accept-Language", defaultValue = "en") @Parameter(description = "Код мови для перекладу") String languageCode) {
        List<LocationWithVisitCountResponse> locations = locationService.findLocationsByTags(tags);
        locations.forEach(location -> locationService.getLocationWithTranslation(location.getId(), languageCode));
        return ResponseEntity.ok(locations);
    }

    @Operation(summary = "Отримати локації за типами",
            description = "Повертає список локацій, відфільтрованих за типами, з кількістю відвідувань")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішно отримано локації",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationWithVisitCountResponse.class))),
            @ApiResponse(responseCode = "400", description = "Невалідні типи локацій"),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера")
    })
    @GetMapping("/by-types")
    public ResponseEntity<List<LocationWithVisitCountResponse>> getLocationsByTypes(
            @RequestParam(required = false) @Parameter(description = "Список типів локацій для фільтрації") List<LocationType> types,
            @RequestHeader(value = "Accept-Language", defaultValue = "en") @Parameter(description = "Код мови для перекладу") String languageCode) {
        List<LocationWithVisitCountResponse> locations = locationService.findLocationsByTypes(types);
        locations.forEach(location -> locationService.getLocationWithTranslation(location.getId(), languageCode));
        return ResponseEntity.ok(locations);
    }
}