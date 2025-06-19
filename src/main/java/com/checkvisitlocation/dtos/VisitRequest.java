package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO для запиту на створення відвідування.
 */
public class VisitRequest {
    @NotNull(message = "Location ID cannot be null")
    private Long locationId;

    @NotBlank(message = "Impressions cannot be blank")
    @Size(max = 1000, message = "Impressions cannot exceed 1000 characters")
    /** Враження користувача. */
    private String impressions;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    /** Рейтинг відвідування. */
    private Integer rating;

    @NotNull(message = "Visit date cannot be null")
    @PastOrPresent(message = "Visit date cannot be in the future")
    /** Дата відвідування. */
    private LocalDate visitDate;

    // Геттери та сеттери
    /** Повертає ідентифікатор локації. */
    public Long getLocationId() {
        return locationId;
    }

    /** Встановлює ідентифікатор локації. */
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    /** Повертає враження користувача. */
    public String getImpressions() {
        return impressions;
    }

    /** Встановлює враження користувача. */
    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    /** Повертає рейтинг відвідування. */
    public Integer getRating() {
        return rating;
    }

    /** Встановлює рейтинг відвідування. */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /** Повертає дату відвідування. */
    public LocalDate getVisitDate() {
        return visitDate;
    }

    /** Встановлює дату відвідування. */
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * Конвертує VisitRequest у сутність Visit
     * @return новий об'єкт Visit з заповненими полями (без user і location)
     */
    public Visit toVisit() {
        Visit visit = new Visit();
        visit.setImpressions(this.impressions);
        visit.setRating(this.rating);
        visit.setVisitDate(this.visitDate);
        return visit;
    }
}