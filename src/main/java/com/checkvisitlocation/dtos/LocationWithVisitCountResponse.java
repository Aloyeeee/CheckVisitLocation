package com.checkvisitlocation.dtos;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;

import java.util.Set;

/**
 * DTO для відповіді з інформацією про локацію та кількість її відвідувань.
 */
public class LocationWithVisitCountResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String geoTag;
    private LocationType type;
    private Set<TagType> tags;
    private long visitCount;

    /** Ідентифікатор локації. */
    public LocationWithVisitCountResponse(Long id, String name, String description, String address, String geoTag,
                                          LocationType type, Set<TagType> tags, long visitCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.geoTag = geoTag;
        this.type = type;
        this.tags = tags;
        this.visitCount = visitCount;
    }

    // Геттери та сеттери
    /** Повертає ідентифікатор локації. */
    public Long getId() { return id; }
    /** Встановлює ідентифікатор локації. */
    public void setId(Long id) { this.id = id; }
    /** Повертає назву локації. */
    public String getName() { return name; }
    /** Встановлює назву локації. */
    public void setName(String name) { this.name = name; }
    /** Повертає опис локації. */
    public String getDescription() { return description; }
    /** Встановлює опис локації. */
    public void setDescription(String description) { this.description = description; }
    /** Повертає адресу локації. */
    public String getAddress() { return address; }
    /** Встановлює адресу локації. */
    public void setAddress(String address) { this.address = address; }
    /** Повертає геотег локації. */
    public String getGeoTag() { return geoTag; }
    /** Встановлює геотег локації. */
    public void setGeoTag(String geoTag) { this.geoTag = geoTag; }
    /** Повертає тип локації. */
    public LocationType getType() { return type; }
    /** Встановлює тип локації. */
    public void setType(LocationType type) { this.type = type; }
    /** Повертає теги локації. */
    public Set<TagType> getTags() { return tags; }
    /** Встановлює теги локації. */
    public void setTags(Set<TagType> tags) { this.tags = tags; }
    /** Повертає кількість відвідувань. */
    public long getVisitCount() { return visitCount; }
    /** Встановлює кількість відвідувань. */
    public void setVisitCount(long visitCount) { this.visitCount = visitCount; }
}