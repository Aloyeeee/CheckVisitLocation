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
 * Сутність "Локація" (Location).
 * Містить інформацію про місце, його тип, координати, теги, відвідування та переклади.
 */
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Location name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    /** Назва локації. */
    private String name;

    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    /** Опис локації. */
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    /** Адреса локації. */
    private String address;

    @Column(name = "geo_tag")
    @Size(max = 100, message = "Geo tag cannot exceed 100 characters")
    @Pattern(regexp = "^-?\\d{1,3}\\.\\d+,-?\\d{1,3}\\.\\d+$", message = "Geo tag must be in format 'latitude,longitude'")
    /** Геотег (координати у форматі "lat,lon"). */
    private String geoTag;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    /** Тип локації (енум LocationType). */
    private LocationType type;

    @ManyToMany
    @JoinTable(
            name = "location_tags",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "location-visits")
    private List<Visit> visits = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "location-translations")
    private List<LocationTranslation> translations = new ArrayList<>();

    // Геттери та сеттери
    /**
     * Повертає ідентифікатор локації.
     */
    public Long getId() { return id; }
    /**
     * Встановлює ідентифікатор локації.
     */
    public void setId(Long id) { this.id = id; }
    /**
     * Повертає назву локації.
     */
    public String getName() { return name; }
    /**
     * Встановлює назву локації.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Повертає опис локації.
     */
    public String getDescription() { return description; }
    /**
     * Встановлює опис локації.
     */
    public void setDescription(String description) { this.description = description; }
    /**
     * Повертає адресу локації.
     */
    public String getAddress() { return address; }
    /**
     * Встановлює адресу локації.
     */
    public void setAddress(String address) { this.address = address; }
    /**
     * Повертає геотег локації.
     */
    public String getGeoTag() { return geoTag; }
    /**
     * Встановлює геотег локації.
     */
    public void setGeoTag(String geoTag) { this.geoTag = geoTag; }
    /**
     * Повертає тип локації.
     */
    public LocationType getType() { return type; }
    /**
     * Встановлює тип локації.
     */
    public void setType(LocationType type) { this.type = type; }
    /**
     * Повертає теги локації.
     */
    public Set<Tag> getTags() { return tags; }
    /**
     * Встановлює теги локації.
     */
    public void setTags(Set<Tag> tags) { this.tags = tags; }
    /**
     * Повертає список відвідувань цієї локації.
     */
    public List<Visit> getVisits() { return visits; }
    /**
     * Встановлює список відвідувань цієї локації.
     */
    public void setVisits(List<Visit> visits) { this.visits = visits; }
    /**
     * Повертає список перекладів цієї локації.
     */
    public List<LocationTranslation> getTranslations() { return translations; }
    /**
     * Встановлює список перекладів цієї локації.
     */
    public void setTranslations(List<LocationTranslation> translations) { this.translations = translations; }
}