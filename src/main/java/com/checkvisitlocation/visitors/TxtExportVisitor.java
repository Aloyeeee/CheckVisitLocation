package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

public class TxtExportVisitor implements DataExportVisitor {
    @Override
    public String visit(Visit visit) {
        return String.format("Visit #%d: %s on %s, Rating: %d, Impressions: %s\n",
                visit.getId(),
                visit.getLocation().getName(),
                visit.getVisitDate().toString(),
                visit.getRating(),
                visit.getImpressions());
    }

    @Override
    public String visit(AnalyticsResponse analytics) {
        return String.format("Analytics:\nAverage Rating: %.2f\nTotal Visits: %d\n",
                analytics.getAverageRating(),
                analytics.getTotalVisits());
    }
}