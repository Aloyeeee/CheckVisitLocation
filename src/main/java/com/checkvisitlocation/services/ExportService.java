package com.checkvisitlocation.services;

import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import com.checkvisitlocation.strategies.CsvExportStrategy;
import com.checkvisitlocation.strategies.ExportStrategy;
import com.checkvisitlocation.strategies.JsonExportStrategy;
import com.checkvisitlocation.strategies.TxtExportStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExportService {
    private final VisitRepository visitRepository;

    public ExportService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public ExportResult exportVisits(User user, ExportFormat format) {
        List<Visit> visits = visitRepository.findByUser(user);
        ExportStrategy strategy = getStrategy(format);
        String content = strategy.export(visits);
        String filename = generateFilename(user, strategy);

        return new ExportResult(content, filename);
    }

    private ExportStrategy getStrategy(ExportFormat format) {
        return switch (format) {
            case CSV -> new CsvExportStrategy();
            case JSON -> new JsonExportStrategy();
            case TEXT -> new TxtExportStrategy();
        };
    }

    private String generateFilename(User user, ExportStrategy strategy) {
        return String.format("%s_visits_%s.%s",
                user.getUsername(),
                LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                strategy.getFileExtension());
    }

    public record ExportResult(String content, String filename) {}
}