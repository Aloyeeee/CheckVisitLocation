package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

public class CsvExportVisitor implements DataExportVisitor {
    @Override
    public String visit(Visit visit) {
        return String.format("%d,%s,%s,%d,%s\n",
                visit.getId(),
                escapeCsv(visit.getLocation().getName()),
                visit.getVisitDate().toString(),
                visit.getRating(),
                escapeCsv(visit.getImpressions()));
    }

    @Override
    public String visit(AnalyticsResponse analytics) {
        return String.format("averageRating,%.2f\ntotalVisits,%d\n",
                analytics.getAverageRating(),
                analytics.getTotalVisits());
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}