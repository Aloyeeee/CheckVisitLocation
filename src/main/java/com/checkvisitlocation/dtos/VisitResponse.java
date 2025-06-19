package com.checkvisitlocation.dtos;

import com.checkvisitlocation.models.Visit;

import java.time.LocalDate;

/**
 * Клас, що представляє відповідь з інформацією про відвідування.
 * Містить детальну інформацію про відвідування, включаючи дані про користувача та локацію.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class VisitResponse {
    /**
     * Унікальний ідентифікатор відвідування.
     */
    private Long id;

    /**
     * Ідентифікатор користувача, який здійснив відвідування.
     */
    private Long userId;

    /**
     * Ім'я користувача, який здійснив відвідування.
     */
    private String username;

    /**
     * Ідентифікатор відвіданої локації.
     */
    private Long locationId;

    /**
     * Назва відвіданої локації.
     */
    private String locationName;

    /**
     * Дата відвідування.
     */
    private LocalDate visitDate;

    /**
     * Враження від відвідування.
     */
    private String impressions;

    /**
     * Рейтинг відвідування.
     */
    private Integer rating;

    /**
     * Створює новий об'єкт відповіді на основі сутності Visit.
     * 
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

    /**
     * Отримує ідентифікатор відвідування.
     * 
     * @return ідентифікатор відвідування
     */
    public Long getId() { return id; }

    /**
     * Встановлює ідентифікатор відвідування.
     * 
     * @param id ідентифікатор відвідування
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Отримує ідентифікатор користувача.
     * 
     * @return ідентифікатор користувача
     */
    public Long getUserId() { return userId; }

    /**
     * Встановлює ідентифікатор користувача.
     * 
     * @param userId ідентифікатор користувача
     */
    public void setUserId(Long userId) { this.userId = userId; }

    /**
     * Отримує ім'я користувача.
     * 
     * @return ім'я користувача
     */
    public String getUsername() { return username; }

    /**
     * Встановлює ім'я користувача.
     * 
     * @param username ім'я користувача
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Отримує ідентифікатор локації.
     * 
     * @return ідентифікатор локації
     */
    public Long getLocationId() { return locationId; }

    /**
     * Встановлює ідентифікатор локації.
     * 
     * @param locationId ідентифікатор локації
     */
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    /**
     * Отримує назву локації.
     * 
     * @return назва локації
     */
    public String getLocationName() { return locationName; }

    /**
     * Встановлює назву локації.
     * 
     * @param locationName назва локації
     */
    public void setLocationName(String locationName) { this.locationName = locationName; }

    /**
     * Отримує дату відвідування.
     * 
     * @return дата відвідування
     */
    public LocalDate getVisitDate() { return visitDate; }

    /**
     * Встановлює дату відвідування.
     * 
     * @param visitDate дата відвідування
     */
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    /**
     * Отримує враження від відвідування.
     * 
     * @return враження від відвідування
     */
    public String getImpressions() { return impressions; }

    /**
     * Встановлює враження від відвідування.
     * 
     * @param impressions враження від відвідування
     */
    public void setImpressions(String impressions) { this.impressions = impressions; }

    /**
     * Отримує рейтинг відвідування.
     * 
     * @return рейтинг відвідування
     */
    public Integer getRating() { return rating; }

    /**
     * Встановлює рейтинг відвідування.
     * 
     * @param rating рейтинг відвідування
     */
    public void setRating(Integer rating) { this.rating = rating; }
}