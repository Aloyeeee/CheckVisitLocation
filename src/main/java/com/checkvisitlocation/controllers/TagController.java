package com.checkvisitlocation.controllers;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Tags", description = "API для управління тегами локацій")
public class TagController {
    private final LocationService locationService;

    public TagController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/{id}/tags")
    @Operation(summary = "Додати теги до локації")
    public ResponseEntity<Location> addTags(
            @PathVariable Long id,
            @RequestBody List<TagType> tagTypes) {
        Location updatedLocation = locationService.addTagsToLocation(id, tagTypes);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}/tags")
    @Operation(summary = "Видалити теги з локації")
    public ResponseEntity<Location> removeTags(
            @PathVariable Long id,
            @RequestBody List<TagType> tagTypes) {
        Location updatedLocation = locationService.removeTagsFromLocation(id, tagTypes);
        return ResponseEntity.ok(updatedLocation);
    }

    @GetMapping
    @Operation(summary = "Отримати локації за тегами")
    public ResponseEntity<List<Location>> getLocationsByTags(
            @RequestParam(required = false) List<TagType> tags,
            @RequestHeader(value = "Accept-Language", defaultValue = "en") String languageCode) {
        List<Location> locations = locationService.findLocationsByTags(tags);
        locations.forEach(location -> locationService.getLocationWithTranslation(location.getId(), languageCode));
        return ResponseEntity.ok(locations);
    }
}