package com.checkvisitlocation.dtos;

import com.checkvisitlocation.enums.LocationType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

/**
 * Клас, що представляє запит на аналіз відвідувань.
 * Містить параметри для фільтрації, сортування та форматування результатів аналізу.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class AnalyticsRequest {
    /**
     * Початкова дата для фільтрації відвідувань.
     * Не може бути в майбутньому.
     */
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    /**
     * Кінцева дата для фільтрації відвідувань.
     * Не може бути в майбутньому.
     */
    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;

    /**
     * Список типів локацій для фільтрації.
     */
    private List<LocationType> locationTypes;

    /**
     * Широта для фільтрації за геолокацією.
     * Має бути в межах від -90 до 90 градусів.
     */
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double latitude;

    /**
     * Довгота для фільтрації за геолокацією.
     * Має бути в межах від -180 до 180 градусів.
     */
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double longitude;

    /**
     * Максимальна відстань для фільтрації за геолокацією.
     * Має бути невід'ємним числом (у кілометрах).
     */
    @DecimalMin(value = "0.0", message = "Distance must be non-negative")
    private Double maxDistance;

    /**
     * Поле для сортування результатів.
     * Можливі значення: date, rating, locationName
     */
    private String sortBy;

    /**
     * Порядок сортування.
     * Можливі значення: asc, desc
     */
    private String sortOrder;

    /**
     * Формат звіту.
     * Можливі значення: text, json
     */
    private String reportFormat;

    /**
     * Отримує початкову дату.
     * 
     * @return початкова дата
     */
    public LocalDate getStartDate() { return startDate; }

    /**
     * Встановлює початкову дату.
     * 
     * @param startDate початкова дата
     */
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    /**
     * Отримує кінцеву дату.
     * 
     * @return кінцева дата
     */
    public LocalDate getEndDate() { return endDate; }

    /**
     * Встановлює кінцеву дату.
     * 
     * @param endDate кінцева дата
     */
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    /**
     * Отримує список типів локацій.
     * 
     * @return список типів локацій
     */
    public List<LocationType> getLocationTypes() { return locationTypes; }

    /**
     * Встановлює список типів локацій.
     * 
     * @param locationTypes список типів локацій
     */
    public void setLocationTypes(List<LocationType> locationTypes) { this.locationTypes = locationTypes; }

    /**
     * Отримує широту.
     * 
     * @return широта
     */
    public Double getLatitude() { return latitude; }

    /**
     * Встановлює широту.
     * 
     * @param latitude широта
     */
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    /**
     * Отримує довготу.
     * 
     * @return довгота
     */
    public Double getLongitude() { return longitude; }

    /**
     * Встановлює довготу.
     * 
     * @param longitude довгота
     */
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    /**
     * Отримує максимальну відстань.
     * 
     * @return максимальна відстань
     */
    public Double getMaxDistance() { return maxDistance; }

    /**
     * Встановлює максимальну відстань.
     * 
     * @param maxDistance максимальна відстань
     */
    public void setMaxDistance(Double maxDistance) { this.maxDistance = maxDistance; }

    /**
     * Отримує поле для сортування.
     * 
     * @return поле для сортування
     */
    public String getSortBy() { return sortBy; }

    /**
     * Встановлює поле для сортування.
     * 
     * @param sortBy поле для сортування
     */
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    /**
     * Отримує порядок сортування.
     * 
     * @return порядок сортування
     */
    public String getSortOrder() { return sortOrder; }

    /**
     * Встановлює порядок сортування.
     * 
     * @param sortOrder порядок сортування
     */
    public void setSortOrder(String sortOrder) { this.sortOrder = sortOrder; }

    /**
     * Отримує формат звіту.
     * 
     * @return формат звіту
     */
    public String getReportFormat() { return reportFormat; }

    /**
     * Встановлює формат звіту.
     * 
     * @param reportFormat формат звіту
     */
    public void setReportFormat(String reportFormat) { this.reportFormat = reportFormat; }
}