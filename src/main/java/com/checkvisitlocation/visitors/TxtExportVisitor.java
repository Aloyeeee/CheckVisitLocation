package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

/**
 * Відвідувач для експорту даних у текстовий формат.
 * Реалізує інтерфейс {@link DataExportVisitor} для Visit та AnalyticsResponse.
 */
public class TxtExportVisitor implements DataExportVisitor {
    /**
     * Експортує об'єкт Visit у текстовий формат.
     * @param visit відвідування для експорту
     * @return текстове представлення відвідування
     */
    @Override
    public String visit(Visit visit) {
        return String.format("Visit #%d: %s on %s, Rating: %d, Impressions: %s\n",
                visit.getId(),
                visit.getLocation().getName(),
                visit.getVisitDate().toString(),
                visit.getRating(),
                visit.getImpressions());
    }

    /**
     * Експортує об'єкт AnalyticsResponse у текстовий формат.
     * @param analytics аналітична відповідь для експорту
     * @return текстове представлення аналітики
     */
    @Override
    public String visit(AnalyticsResponse analytics) {
        return String.format("Analytics:\nAverage Rating: %.2f\nTotal Visits: %d\n",
                analytics.getAverageRating(),
                analytics.getTotalVisits());
    }
}