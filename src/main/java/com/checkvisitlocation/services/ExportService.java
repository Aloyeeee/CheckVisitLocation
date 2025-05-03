package com.checkvisitlocation.services;

import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {
    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);
    private final VisitRepository visitRepository;
    private final ObjectMapper objectMapper;

    public ExportService(VisitRepository visitRepository, ObjectMapper objectMapper) {
        this.visitRepository = visitRepository;
        this.objectMapper = objectMapper;
    }

    public ExportResult exportVisits(User user, ExportFormat format) {
        List<Visit> visits = visitRepository.findByUser(user);
        String filename = "visits_" + user.getUsername() + "." + format.getExtension();
        String content;

        try {
            content = switch (format) {
                case CSV -> toCsv(visits);
                case JSON -> toJson(visits);
                case TEXT -> toText(visits);
            };
        } catch (Exception e) {
            logger.error("Failed to export visits for user {} in format {}: {}", user.getUsername(), format, e.getMessage());
            throw new IllegalStateException("Failed to export visits: " + e.getMessage());
        }

        return new ExportResult(filename, content);
    }

    private String toCsv(List<Visit> visits) {
        StringBuilder csv = new StringBuilder("id,location_name,visit_date,impressions,rating\n");
        for (Visit visit : visits) {
            if (visit.getLocation() == null) {
                logger.warn("Visit {} has null location", visit.getId());
                continue;
            }
            csv.append(String.format("%d,%s,%s,%s,%d\n",
                    visit.getId(),
                    escapeCsv(visit.getLocation().getName()),
                    visit.getVisitDate(),
                    escapeCsv(visit.getImpressions() != null ? visit.getImpressions() : ""),
                    visit.getRating()));
        }
        return csv.toString();
    }

    private String toJson(List<Visit> visits) throws Exception {
        return objectMapper.writeValueAsString(visits);
    }

    private String toText(List<Visit> visits) {
        return visits.stream()
                .filter(visit -> visit.getLocation() != null)
                .map(visit -> String.format("Visit #%d: %s on %s, Rating: %d, Impressions: %s",
                        visit.getId(),
                        visit.getLocation().getName(),
                        visit.getVisitDate(),
                        visit.getRating(),
                        visit.getImpressions() != null ? visit.getImpressions() : ""))
                .collect(Collectors.joining("\n"));
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public record ExportResult(String filename, String content) {}
}
