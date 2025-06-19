package com.checkvisitlocation.models;

import com.checkvisitlocation.enums.LocationType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сутність, що представляє локацію для відвідування.
 * Містить інформацію про місце, його характеристики та зв'язки з іншими сутностями.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "locations")
public class Location {
    /**
     * Унікальний ідентифікатор локації.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Назва локації.
     * Не може бути порожньою і повинна бути від 2 до 255 символів.
     */
    @Column(nullable = false)
    @NotBlank(message = "Location name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    /**
     * Опис локації.
     * Не може перевищувати 1000 символів.
     */
    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    /**
     * Фізична адреса локації.
     * Не може бути порожньою і не може перевищувати 255 символів.
     */
    @Column(nullable = false)
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    /**
     * Географічні координати локації.
     * Повинні бути у форматі 'широта,довгота' і не перевищувати 100 символів.
     */
    @Column(name = "geo_tag")
    @Size(max = 100, message = "Geo tag cannot exceed 100 characters")
    @Pattern(regexp = "^-?\\d{1,3}\\.\\d+,-?\\d{1,3}\\.\\d+$", message = "Geo tag must be in format 'latitude,longitude'")
    private String geoTag;

    /**
     * Тип локації.
     * Не може бути null.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType type;

    /**
     * Набір тегів, пов'язаних з локацією.
     * Зв'язок багато-до-багатьох з сутністю Tag.
     */
    @ManyToMany
    @JoinTable(
            name = "location_tags",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    /**
     * Список відвідувань цієї локації.
     * Зв'язок один-до-багатьох з сутністю Visit.
     * Завантажується ледаче (lazy loading).
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "location-visits")
    private List<Visit> visits = new ArrayList<>();

    /**
     * Список перекладів інформації про локацію.
     * Зв'язок один-до-багатьох з сутністю LocationTranslation.
     * Завантажується ледаче (lazy loading).
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "location-translations")
    private List<LocationTranslation> translations = new ArrayList<>();

    /**
     * Отримує ідентифікатор локації.
     * 
     * @return ідентифікатор локації
     */
    public Long getId() { return id; }

    /**
     * Встановлює ідентифікатор локації.
     * 
     * @param id ідентифікатор локації
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Отримує назву локації.
     * 
     * @return назва локації
     */
    public String getName() { return name; }

    /**
     * Встановлює назву локації.
     * 
     * @param name назва локації
     */
    public void setName(String name) { this.name = name; }

    /**
     * Отримує опис локації.
     * 
     * @return опис локації
     */
    public String getDescription() { return description; }

    /**
     * Встановлює опис локації.
     * 
     * @param description опис локації
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Отримує адресу локації.
     * 
     * @return адреса локації
     */
    public String getAddress() { return address; }

    /**
     * Встановлює адресу локації.
     * 
     * @param address адреса локації
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Отримує географічні координати локації.
     * 
     * @return географічні координати
     */
    public String getGeoTag() { return geoTag; }

    /**
     * Встановлює географічні координати локації.
     * 
     * @param geoTag географічні координати
     */
    public void setGeoTag(String geoTag) { this.geoTag = geoTag; }

    /**
     * Отримує тип локації.
     * 
     * @return тип локації
     */
    public LocationType getType() { return type; }

    /**
     * Встановлює тип локації.
     * 
     * @param type тип локації
     */
    public void setType(LocationType type) { this.type = type; }

    /**
     * Отримує набір тегів локації.
     * 
     * @return набір тегів
     */
    public Set<Tag> getTags() { return tags; }

    /**
     * Встановлює набір тегів локації.
     * 
     * @param tags набір тегів
     */
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    /**
     * Отримує список відвідувань локації.
     * 
     * @return список відвідувань
     */
    public List<Visit> getVisits() { return visits; }

    /**
     * Встановлює список відвідувань локації.
     * 
     * @param visits список відвідувань
     */
    public void setVisits(List<Visit> visits) { this.visits = visits; }

    /**
     * Отримує список перекладів інформації про локацію.
     * 
     * @return список перекладів
     */
    public List<LocationTranslation> getTranslations() { return translations; }

    /**
     * Встановлює список перекладів інформації про локацію.
     * 
     * @param translations список перекладів
     */
    public void setTranslations(List<LocationTranslation> translations) { this.translations = translations; }
}