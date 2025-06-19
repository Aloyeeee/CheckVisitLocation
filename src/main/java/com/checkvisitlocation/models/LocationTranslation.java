package com.checkvisitlocation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Сутність "Переклад локації" (LocationTranslation).
 * Містить локалізовані назву та опис для локації певною мовою.
 */
@Entity
@Table(name = "location_translations")
public class LocationTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    @Column(nullable = false)
    @NotBlank(message = "Language code cannot be blank")
    private String languageCode;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    /** Ідентифікатор перекладу. */
    public Long getId() { return id; }
    /**
     * Встановлює ідентифікатор перекладу.
     */
    public void setId(Long id) { this.id = id; }

    /** Локація, до якої належить переклад. */
    public Location getLocation() { return location; }
    /**
     * Встановлює локацію, до якої належить переклад.
     */
    public void setLocation(Location location) { this.location = location; }

    /** Код мови (наприклад, "uk", "en"). */
    public String getLanguageCode() { return languageCode; }
    /**
     * Встановлює код мови.
     */
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }

    /** Локалізована назва локації. */
    public String getName() { return name; }
    /**
     * Встановлює локалізовану назву локації.
     */
    public void setName(String name) { this.name = name; }

    /** Локалізований опис локації. */
    public String getDescription() { return description; }
    /**
     * Встановлює локалізований опис локації.
     */
    public void setDescription(String description) { this.description = description; }
}