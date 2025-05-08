package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;

import java.time.LocalDate;

public class VisitResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long locationId;
    private String locationName;
    private LocalDate visitDate;
    private String impressions;
    private Integer rating;

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

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    public String getImpressions() { return impressions; }
    public void setImpressions(String impressions) { this.impressions = impressions; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}