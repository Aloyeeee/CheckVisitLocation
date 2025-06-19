package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.visitors.DataExportVisitor;
import java.util.List;
import java.util.Map;

/**
 * DTO для відповіді з аналітикою відвідувань.
 */
public class AnalyticsResponse {
    private double averageRating;
    private long totalVisits;
    private Map<String, Long> visitsByType;
    private List<Visit> filteredVisits;
    private String report;

    /**
     * Конструктор за замовчуванням.
     */
    public AnalyticsResponse() {}

    /** Повертає середній рейтинг. */
    public double getAverageRating() { return averageRating; }
    /** Встановлює середній рейтинг. */
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    /** Повертає загальну кількість відвідувань. */
    public long getTotalVisits() { return totalVisits; }
    /** Встановлює загальну кількість відвідувань. */
    public void setTotalVisits(long totalVisits) { this.totalVisits = totalVisits; }

    /** Повертає кількість відвідувань за типами локацій. */
    public Map<String, Long> getVisitsByType() { return visitsByType; }
    /** Встановлює кількість відвідувань за типами локацій. */
    public void setVisitsByType(Map<String, Long> visitsByType) { this.visitsByType = visitsByType; }

    /** Повертає відфільтрований список відвідувань. */
    public List<Visit> getFilteredVisits() { return filteredVisits; }
    /** Встановлює відфільтрований список відвідувань. */
    public void setFilteredVisits(List<Visit> filteredVisits) { this.filteredVisits = filteredVisits; }

    /** Повертає звіт у вигляді рядка. */
    public String getReport() { return report; }
    /** Встановлює звіт у вигляді рядка. */
    public void setReport(String report) { this.report = report; }

    /**
     * Приймає відвідувача для експорту (патерн Visitor).
     * @param visitor відвідувач
     * @return результат експорту
     */
    public String accept(DataExportVisitor visitor) {
        return visitor.visit(this);
    }
}