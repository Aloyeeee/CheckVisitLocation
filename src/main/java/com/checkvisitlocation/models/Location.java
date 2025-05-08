package com.checkvisitlocation.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Location name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @Column(name = "geo_tag")
    @Size(max = 100, message = "Geo tag cannot exceed 100 characters")
    @Pattern(regexp = "^-?\\d{1,3}\\.\\d+,-?\\d{1,3}\\.\\d+$", message = "Geo tag must be in format 'latitude,longitude'")
    private String geoTag;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getGeoTag() { return geoTag; }
    public void setGeoTag(String geoTag) { this.geoTag = geoTag; }
    public LocationType getType() { return type; }
    public void setType(LocationType type) { this.type = type; }
    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
    public List<Visit> getVisits() { return visits; }
    public void setVisits(List<Visit> visits) { this.visits = visits; }
    public List<LocationTranslation> getTranslations() { return translations; }
    public void setTranslations(List<LocationTranslation> translations) { this.translations = translations; }
}