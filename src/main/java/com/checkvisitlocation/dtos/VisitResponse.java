package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;

import java.time.LocalDate;

/**
 * DTO для відповіді з інформацією про відвідування.
 */
public class VisitResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long locationId;
    private String locationName;
    private LocalDate visitDate;
    private String impressions;
    private Integer rating;

    /**
     * Конструктор VisitResponse на основі Visit.
     * @param visit сутність відвідування
     */
    public VisitResponse(Visit visit) {
        this.id = visit.getId();
        this.userId = visit.getUser().getId();
        this.username = visit.getUser().getUsername();
        this.locationId = visit.getLocation().getId();
        this.locationName = visit.getLocation().getName();
        this.visitDate = visit.getVisitDate();
        this.impressions = visit.getImpressions();
        this.rating = visit.getRating();
    }

    /** Повертає ідентифікатор відвідування. */
    public Long getId() { return id; }
    /** Встановлює ідентифікатор відвідування. */
    public void setId(Long id) { this.id = id; }
    /** Повертає ідентифікатор користувача. */
    public Long getUserId() { return userId; }
    /** Встановлює ідентифікатор користувача. */
    public void setUserId(Long userId) { this.userId = userId; }
    /** Повертає ім'я користувача. */
    public String getUsername() { return username; }
    /** Встановлює ім'я користувача. */
    public void setUsername(String username) { this.username = username; }
    /** Повертає ідентифікатор локації. */
    public Long getLocationId() { return locationId; }
    /** Встановлює ідентифікатор локації. */
    public void setLocationId(Long locationId) { this.locationId = locationId; }
    /** Повертає назву локації. */
    public String getLocationName() { return locationName; }
    /** Встановлює назву локації. */
    public void setLocationName(String locationName) { this.locationName = locationName; }
    /** Повертає дату відвідування. */
    public LocalDate getVisitDate() { return visitDate; }
    /** Встановлює дату відвідування. */
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    /** Повертає враження користувача. */
    public String getImpressions() { return impressions; }
    /** Встановлює враження користувача. */
    public void setImpressions(String impressions) { this.impressions = impressions; }
    /** Повертає рейтинг відвідування. */
    public Integer getRating() { return rating; }
    /** Встановлює рейтинг відвідування. */
    public void setRating(Integer rating) { this.rating = rating; }
}