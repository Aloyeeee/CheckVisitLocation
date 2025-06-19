package com.checkvisitlocation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Сутність, що представляє переклад інформації про локацію.
 * Дозволяє зберігати назву та опис локації різними мовами.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "location_translations")
public class LocationTranslation {
    /**
     * Унікальний ідентифікатор перекладу.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Локація, до якої належить переклад.
     * Завантажується ледаче (lazy loading).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    /**
     * Код мови перекладу.
     * Не може бути порожнім.
     */
    @Column(nullable = false)
    @NotBlank(message = "Language code cannot be blank")
    private String languageCode;

    /**
     * Назва локації в даній мові.
     * Не може бути порожньою і повинна бути від 2 до 255 символів.
     */
    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    /**
     * Опис локації в даній мові.
     * Не може перевищувати 1000 символів.
     */
    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    /**
     * Отримує ідентифікатор перекладу.
     * 
     * @return ідентифікатор перекладу
     */
    public Long getId() { return id; }

    /**
     * Встановлює ідентифікатор перекладу.
     * 
     * @param id ідентифікатор перекладу
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Отримує локацію, до якої належить переклад.
     * 
     * @return локація
     */
    public Location getLocation() { return location; }

    /**
     * Встановлює локацію для перекладу.
     * 
     * @param location локація
     */
    public void setLocation(Location location) { this.location = location; }

    /**
     * Отримує код мови перекладу.
     * 
     * @return код мови
     */
    public String getLanguageCode() { return languageCode; }

    /**
     * Встановлює код мови перекладу.
     * 
     * @param languageCode код мови
     */
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }

    /**
     * Отримує назву локації в даній мові.
     * 
     * @return назва локації
     */
    public String getName() { return name; }

    /**
     * Встановлює назву локації в даній мові.
     * 
     * @param name назва локації
     */
    public void setName(String name) { this.name = name; }

    /**
     * Отримує опис локації в даній мові.
     * 
     * @return опис локації
     */
    public String getDescription() { return description; }

    /**
     * Встановлює опис локації в даній мові.
     * 
     * @param description опис локації
     */
    public void setDescription(String description) { this.description = description; }
}