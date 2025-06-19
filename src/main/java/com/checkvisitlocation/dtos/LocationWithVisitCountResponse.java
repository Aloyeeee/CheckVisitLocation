package com.checkvisitlocation.dtos;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;

import java.util.Set;

/**
 * Клас, що представляє відповідь з інформацією про локацію та кількістю її відвідувань.
 * Використовується для відображення детальної інформації про локацію разом зі статистикою відвідувань.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class LocationWithVisitCountResponse {
    /**
     * Унікальний ідентифікатор локації.
     */
    private Long id;

    /**
     * Назва локації.
     */
    private String name;

    /**
     * Опис локації.
     */
    private String description;

    /**
     * Фізична адреса локації.
     */
    private String address;

    /**
     * Географічні координати локації.
     */
    private String geoTag;

    /**
     * Тип локації.
     */
    private LocationType type;

    /**
     * Набір тегів, пов'язаних з локацією.
     */
    private Set<TagType> tags;

    /**
     * Кількість відвідувань локації.
     */
    private long visitCount;

    /**
     * Створює новий об'єкт відповіді з інформацією про локацію.
     * 
     * @param id ідентифікатор локації
     * @param name назва локації
     * @param description опис локації
     * @param address адреса локації
     * @param geoTag географічні координати
     * @param type тип локації
     * @param tags набір тегів
     * @param visitCount кількість відвідувань
     */
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
     * Отримує географічні координати.
     * 
     * @return географічні координати
     */
    public String getGeoTag() { return geoTag; }

    /**
     * Встановлює географічні координати.
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
     * Отримує набір тегів.
     * 
     * @return набір тегів
     */
    public Set<TagType> getTags() { return tags; }

    /**
     * Встановлює набір тегів.
     * 
     * @param tags набір тегів
     */
    public void setTags(Set<TagType> tags) { this.tags = tags; }

    /**
     * Отримує кількість відвідувань.
     * 
     * @return кількість відвідувань
     */
    public long getVisitCount() { return visitCount; }

    /**
     * Встановлює кількість відвідувань.
     * 
     * @param visitCount кількість відвідувань
     */
    public void setVisitCount(long visitCount) { this.visitCount = visitCount; }
}