package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;

import java.util.List;
import java.util.Map;

/**
 * Клас, що представляє відповідь на запит аналізу відвідувань.
 * Містить статистичні дані та відфільтровані відвідування.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class AnalyticsResponse {
    /**
     * Середній рейтинг відвідувань.
     */
    private Double averageRating;

    /**
     * Загальна кількість відвідувань.
     */
    private Long totalVisits;

    /**
     * Кількість відвідувань за типами локацій.
     * Ключ - тип локації, значення - кількість відвідувань.
     */
    private Map<String, Long> visitsByType;

    /**
     * Список відфільтрованих відвідувань.
     */
    private List<Visit> filteredVisits;

    /**
     * Текстовий звіт про аналіз відвідувань.
     */
    private String report;

    /**
     * Отримує середній рейтинг.
     * 
     * @return середній рейтинг
     */
    public Double getAverageRating() { return averageRating; }

    /**
     * Встановлює середній рейтинг.
     * 
     * @param averageRating середній рейтинг
     */
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }

    /**
     * Отримує загальну кількість відвідувань.
     * 
     * @return загальна кількість відвідувань
     */
    public Long getTotalVisits() { return totalVisits; }

    /**
     * Встановлює загальну кількість відвідувань.
     * 
     * @param totalVisits загальна кількість відвідувань
     */
    public void setTotalVisits(Long totalVisits) { this.totalVisits = totalVisits; }

    /**
     * Отримує кількість відвідувань за типами.
     * 
     * @return карта типів локацій та кількості відвідувань
     */
    public Map<String, Long> getVisitsByType() { return visitsByType; }

    /**
     * Встановлює кількість відвідувань за типами.
     * 
     * @param visitsByType карта типів локацій та кількості відвідувань
     */
    public void setVisitsByType(Map<String, Long> visitsByType) { this.visitsByType = visitsByType; }

    /**
     * Отримує список відфільтрованих відвідувань.
     * 
     * @return список відвідувань
     */
    public List<Visit> getFilteredVisits() { return filteredVisits; }

    /**
     * Встановлює список відфільтрованих відвідувань.
     * 
     * @param filteredVisits список відвідувань
     */
    public void setFilteredVisits(List<Visit> filteredVisits) { this.filteredVisits = filteredVisits; }

    /**
     * Отримує текстовий звіт.
     * 
     * @return текстовий звіт
     */
    public String getReport() { return report; }

    /**
     * Встановлює текстовий звіт.
     * 
     * @param report текстовий звіт
     */
    public void setReport(String report) { this.report = report; }
}