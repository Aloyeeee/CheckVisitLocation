package com.checkvisitlocation.controllers;

import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.services.ExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
public class ExportController {
    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/{format}")
    public ResponseEntity<String> exportData(
            @AuthenticationPrincipal User user,
            @PathVariable ExportFormat format) {

        var result = exportService.exportVisits(user, format);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + result.filename() + "\"")
                .contentType(getMediaType(format))
                .body(result.content());
    }

    private MediaType getMediaType(ExportFormat format) {
        return switch (format) {
            case CSV -> MediaType.parseMediaType("text/csv");
            case JSON -> MediaType.APPLICATION_JSON;
            case TEXT -> MediaType.TEXT_PLAIN;
        };
    }
}