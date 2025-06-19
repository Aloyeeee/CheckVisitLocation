package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.checkvisitlocation.dtos.ExportResult;
import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import com.checkvisitlocation.visitors.CsvExportVisitor;
import com.checkvisitlocation.visitors.DataExportVisitor;
import com.checkvisitlocation.visitors.JsonExportVisitor;
import com.checkvisitlocation.visitors.TxtExportVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервіс для експорту відвідувань та аналітики у різні формати (CSV, JSON, TXT).
 */
@Service
public class ExportService {

    @Autowired
    private VisitRepository visitRepository;

    /**
     * Повертає відповідного відвідувача для експорту згідно з форматом.
     * @param format формат експорту
     * @return реалізація DataExportVisitor
     */
    private DataExportVisitor getVisitor(ExportFormat format) {
        return switch (format) {
            case CSV -> new CsvExportVisitor();
            case JSON -> new JsonExportVisitor();
            case TXT -> new TxtExportVisitor();
        };
    }

    /**
     * Експортує всі відвідування користувача у заданому форматі.
     * @param user користувач
     * @param format формат експорту
     * @return результат експорту (файл та вміст)
     */
    public ExportResult exportVisits(User user, ExportFormat format) {
        List<Visit> visits = visitRepository.findByUser(user);
        DataExportVisitor visitor = getVisitor(format);
        StringBuilder content = new StringBuilder();

        if (format == ExportFormat.CSV) {
            content.append("id,location,visitDate,rating,impressions\n");
        } else if (format == ExportFormat.JSON) {
            content.append("[");
        }

        for (int i = 0; i < visits.size(); i++) {
            String visitStr = visits.get(i).accept(visitor);
            content.append(visitStr);
            if (format == ExportFormat.JSON && i < visits.size() - 1) {
                content.append(",");
            }
        }

        if (format == ExportFormat.JSON) {
            content.append("]");
        }

        String filename = "visits_" + user.getUsername() + "." + format.getExtension();
        return new ExportResult(filename, content.toString());
    }

    /**
     * Експортує аналітичні дані у заданому форматі.
     * @param analytics аналітична відповідь
     * @param format формат експорту
     * @return результат експорту (файл та вміст)
     */
    public ExportResult exportAnalytics(AnalyticsResponse analytics, ExportFormat format) {
        DataExportVisitor visitor = getVisitor(format);
        String content = analytics.accept(visitor);
        String filename = "analytics." + format.getExtension();
        return new ExportResult(filename, content);
    }
}