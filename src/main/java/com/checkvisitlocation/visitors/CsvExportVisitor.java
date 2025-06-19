package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

/**
 * Відвідувач для експорту даних у формат CSV.
 * Реалізує інтерфейс {@link DataExportVisitor} для Visit та AnalyticsResponse.
 */
public class CsvExportVisitor implements DataExportVisitor {
    /**
     * Експортує об'єкт Visit у формат CSV.
     * @param visit відвідування для експорту
     * @return CSV-рядок з даними відвідування
     */
    @Override
    public String visit(Visit visit) {
        return String.format("%d,%s,%s,%d,%s\n",
                visit.getId(),
                escapeCsv(visit.getLocation().getName()),
                visit.getVisitDate().toString(),
                visit.getRating(),
                escapeCsv(visit.getImpressions()));
    }

    /**
     * Експортує об'єкт AnalyticsResponse у формат CSV.
     * @param analytics аналітична відповідь для експорту
     * @return CSV-рядок з аналітичними даними
     */
    @Override
    public String visit(AnalyticsResponse analytics) {
        return String.format("averageRating,%.2f\ntotalVisits,%d\n",
                analytics.getAverageRating(),
                analytics.getTotalVisits());
    }

    /**
     * Екранує значення для CSV (додає лапки, якщо потрібно).
     * @param value значення для екранування
     * @return екрановане значення
     */
    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}