package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Клас, що представляє запит на створення нового відвідування.
 * Містить дані про відвідування локації, включаючи враження, рейтинг та дату.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class VisitRequest {
    /**
     * Ідентифікатор локації, яку відвідали.
     * Не може бути null.
     */
    @NotNull(message = "Location ID cannot be null")
    private Long locationId;

    /**
     * Враження від відвідування.
     * Не може бути порожнім та має бути не більше 1000 символів.
     */
    @NotBlank(message = "Impressions cannot be blank")
    @Size(max = 1000, message = "Impressions cannot exceed 1000 characters")
    private String impressions;

    /**
     * Рейтинг відвідування.
     * Має бути від 1 до 5.
     */
    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer rating;

    /**
     * Дата відвідування.
     * Не може бути в майбутньому.
     */
    @NotNull(message = "Visit date cannot be null")
    @PastOrPresent(message = "Visit date cannot be in the future")
    private LocalDate visitDate;

    /**
     * Отримує ідентифікатор локації.
     * 
     * @return ідентифікатор локації
     */
    public Long getLocationId() {
        return locationId;
    }

    /**
     * Встановлює ідентифікатор локації.
     * 
     * @param locationId ідентифікатор локації
     */
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    /**
     * Отримує враження від відвідування.
     * 
     * @return враження від відвідування
     */
    public String getImpressions() {
        return impressions;
    }

    /**
     * Встановлює враження від відвідування.
     * 
     * @param impressions враження від відвідування
     */
    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    /**
     * Отримує рейтинг відвідування.
     * 
     * @return рейтинг відвідування
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Встановлює рейтинг відвідування.
     * 
     * @param rating рейтинг відвідування
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Отримує дату відвідування.
     * 
     * @return дата відвідування
     */
    public LocalDate getVisitDate() {
        return visitDate;
    }

    /**
     * Встановлює дату відвідування.
     * 
     * @param visitDate дата відвідування
     */
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * Конвертує VisitRequest у сутність Visit.
     * Створює новий об'єкт Visit з заповненими полями (без user і location).
     * 
     * @return новий об'єкт Visit
     */
    public Visit toVisit() {
        Visit visit = new Visit();
        visit.setImpressions(this.impressions);
        visit.setRating(this.rating);
        visit.setVisitDate(this.visitDate);
        return visit;
    }
}   