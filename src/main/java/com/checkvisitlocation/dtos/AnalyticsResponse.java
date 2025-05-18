package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.visitors.DataExportVisitor;
import java.util.List;
import java.util.Map;

public class AnalyticsResponse {
    private double averageRating;
    private long totalVisits;
    private Map<String, Long> visitsByType;
    private List<Visit> filteredVisits;
    private String report;

    // Конструктори, гетери та сетери
    public AnalyticsResponse() {}

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    public long getTotalVisits() { return totalVisits; }
    public void setTotalVisits(long totalVisits) { this.totalVisits = totalVisits; }

    public Map<String, Long> getVisitsByType() { return visitsByType; }
    public void setVisitsByType(Map<String, Long> visitsByType) { this.visitsByType = visitsByType; }

    public List<Visit> getFilteredVisits() { return filteredVisits; }
    public void setFilteredVisits(List<Visit> filteredVisits) { this.filteredVisits = filteredVisits; }

    public String getReport() { return report; }
    public void setReport(String report) { this.report = report; }

    // Додано метод accept для патерну Visitor
    public String accept(DataExportVisitor visitor) {
        return visitor.visit(this);
    }
}