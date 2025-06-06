package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class VisitRequest {
    @NotNull(message = "Location ID cannot be null")
    private Long locationId;

    @NotBlank(message = "Impressions cannot be blank")
    @Size(max = 1000, message = "Impressions cannot exceed 1000 characters")
    private String impressions;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer rating;

    @NotNull(message = "Visit date cannot be null")
    @PastOrPresent(message = "Visit date cannot be in the future")
    private LocalDate visitDate;

    // Геттери та сеттери
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

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