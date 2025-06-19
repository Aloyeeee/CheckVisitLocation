package com.checkvisitlocation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Сутність, що представляє відвідування локації користувачем.
 * Містить інформацію про відвідування, включаючи дату, враження та рейтинг.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "visits")
public class Visit {
    /**
     * Унікальний ідентифікатор відвідування.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Користувач, який здійснив відвідування.
     * Не може бути null.
     * Завантажується одразу (eager loading).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * Локація, яка була відвідана.
     * Не може бути null.
     * Завантажується одразу (eager loading).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = false)
    @NotNull(message = "Location cannot be null")
    @JsonBackReference
    private Location location;

    /**
     * Дата відвідування.
     * Не може бути null.
     */
    @Column(nullable = false)
    @NotNull(message = "Visit date cannot be null")
    private LocalDate visitDate;

    /**
     * Враження від відвідування.
     * Не може перевищувати 1000 символів.
     */
    @Column(length = 1000)
    @Size(max = 1000, message = "Impressions cannot exceed 1000 characters")
    private String impressions;

    /**
     * Рейтинг відвідування.
     * Не може бути null і повинен бути від 1 до 5.
     */
    @Column(nullable = false)
    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer rating;

    /**
     * Отримує ідентифікатор відвідування.
     * 
     * @return ідентифікатор відвідування
     */
    public Long getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор відвідування.
     * 
     * @param id ідентифікатор відвідування
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Отримує користувача, який здійснив відвідування.
     * 
     * @return користувач
     */
    public User getUser() {
        return user;
    }

    /**
     * Встановлює користувача для відвідування.
     * 
     * @param user користувач
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Отримує локацію, яка була відвідана.
     * 
     * @return локація
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Встановлює локацію для відвідування.
     * 
     * @param location локація
     */
    public void setLocation(Location location) {
        this.location = location;
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
}