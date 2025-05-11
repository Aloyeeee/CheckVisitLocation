package com.checkvisitlocation.dtos;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;

import java.util.Set;

public class LocationWithVisitCountResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String geoTag;
    private LocationType type;
    private Set<TagType> tags;
    private long visitCount;

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
    public Set<TagType> getTags() { return tags; }
    public void setTags(Set<TagType> tags) { this.tags = tags; }
    public long getVisitCount() { return visitCount; }
    public void setVisitCount(long visitCount) { this.visitCount = visitCount; }
}