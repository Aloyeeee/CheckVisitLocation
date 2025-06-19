package com.checkvisitlocation.controllers;

import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.services.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Контролер для експорту даних про відвідування.
 * Надає можливість експортувати дані у різних форматах (CSV, JSON, TXT).
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/export")
@Tag(name = "Export", description = "API для експорту даних")
public class ExportController {
    private final ExportService exportService;

    /**
     * Створює новий екземпляр контролера експорту.
     * 
     * @param exportService сервіс для експорту даних
     */
    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    /**
     * Експортує дані про відвідування у вказаному форматі.
     * 
     * @param user поточний авторизований користувач
     * @param format формат експорту (CSV, JSON, TXT)
     * @return ResponseEntity з експортованими даними у відповідному форматі
     */
    @GetMapping("/{format}")
    @Operation(
            summary = "Експорт відвідувань у вказаному форматі",
            parameters = {
                    @Parameter(
                            name = "format",
                            description = "Формат експорту (CSV, JSON, TEXT)",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(implementation = ExportFormat.class))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл з експортованими даними",
                            content = {
                                    @Content(mediaType = "text/csv"),
                                    @Content(mediaType = "application/json"),
                                    @Content(mediaType = "text/plain")
                            })
            }
    )
    public ResponseEntity<?> exportData(
            @Parameter(hidden = true) @AuthenticationPrincipal User user,
            @PathVariable ExportFormat format) {
        try {
            var result = exportService.exportVisits(user, format);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + result.filename() + "\"")
                    .contentType(getMediaType(format))
                    .body(result.content());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Export failed: " + e.getMessage());
        }
    }

    /**
     * Визначає MediaType на основі формату експорту.
     * 
     * @param format формат експорту
     * @return відповідний MediaType для формату
     */
    private MediaType getMediaType(ExportFormat format) {
        return switch (format) {
            case CSV -> MediaType.parseMediaType("text/csv");
            case JSON -> MediaType.APPLICATION_JSON;
            case TXT -> MediaType.TEXT_PLAIN;
        };
    }
}