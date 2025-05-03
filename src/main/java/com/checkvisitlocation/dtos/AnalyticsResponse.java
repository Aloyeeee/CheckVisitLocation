package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;

import java.util.List;
import java.util.Map;

public class AnalyticsResponse {
    private Double averageRating;
    private Long totalVisits;
    private Map<String, Long> visitsByType;
    private List<Visit> filteredVisits;
    private String report;

    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    public Long getTotalVisits() { return totalVisits; }
    public void setTotalVisits(Long totalVisits) { this.totalVisits = totalVisits; }
    public Map<String, Long> getVisitsByType() { return visitsByType; }
    public void setVisitsByType(Map<String, Long> visitsByType) { this.visitsByType = visitsByType; }
    public List<Visit> getFilteredVisits() { return filteredVisits; }
    public void setFilteredVisits(List<Visit> filteredVisits) { this.filteredVisits = filteredVisits; }
    public String getReport() { return report; }
    public void setReport(String report) { this.report = report; }
}