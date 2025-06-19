package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.LocationWithVisitCountResponse;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.repositories.LocationRepository;
import com.checkvisitlocation.repositories.LocationTranslationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервіс для роботи з локаціями.
 * Надає методи для пошуку локацій за тегами та типами,
 * а також для роботи з перекладами локацій.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationTranslationRepository translationRepository;

    /**
     * Конструктор для ініціалізації сервісу.
     * 
     * @param locationRepository репозиторій для роботи з локаціями
     * @param translationRepository репозиторій для роботи з перекладами
     */
    public LocationService(LocationRepository locationRepository, LocationTranslationRepository translationRepository) {
        this.locationRepository = locationRepository;
        this.translationRepository = translationRepository;
    }

    /**
     * Знаходить локації за тегами з кількістю відвідувань.
     * Метод виконується в режимі тільки для читання.
     * 
     * @param tags список типів тегів для фільтрації
     * @return список локацій з кількістю відвідувань
     */
    @Transactional(readOnly = true)
    public List<LocationWithVisitCountResponse> findLocationsByTags(List<TagType> tags) {
        List<Object[]> results = tags == null || tags.isEmpty()
                ? locationRepository.findAllWithVisitCount()
                : locationRepository.findByTagTypesWithVisitCount(tags);

        return results.stream().map(result -> {
            Location location = (Location) result[0];
            Long visitCount = (Long) result[1];
            return new LocationWithVisitCountResponse(
                    location.getId(),
                    location.getName(),
                    location.getDescription(),
                    location.getAddress(),
                    location.getGeoTag(),
                    location.getType(),
                    location.getTags().stream().map(Tag::getType).collect(Collectors.toSet()),
                    visitCount
            );
        }).collect(Collectors.toList());
    }

    /**
     * Знаходить локації за типами з кількістю відвідувань.
     * Метод виконується в режимі тільки для читання.
     * 
     * @param types список типів локацій для фільтрації
     * @return список локацій з кількістю відвідувань
     */
    @Transactional(readOnly = true)
    public List<LocationWithVisitCountResponse> findLocationsByTypes(List<LocationType> types) {
        List<Object[]> results = types == null || types.isEmpty()
                ? locationRepository.findAllWithVisitCount()
                : locationRepository.findByTypesWithVisitCount(types);

        return results.stream().map(result -> {
            Location location = (Location) result[0];
            Long visitCount = (Long) result[1];
            return new LocationWithVisitCountResponse(
                    location.getId(),
                    location.getName(),
                    location.getDescription(),
                    location.getAddress(),
                    location.getGeoTag(),
                    location.getType(),
                    location.getTags().stream().map(Tag::getType).collect(Collectors.toSet()),
                    visitCount
            );
        }).collect(Collectors.toList());
    }

    /**
     * Отримує локацію з перекладом для вказаної мови.
     * Метод виконується в режимі тільки для читання.
     * 
     * @param locationId ідентифікатор локації
     * @param languageCode код мови для перекладу
     * @throws IllegalArgumentException якщо локація не знайдена
     */
    @Transactional(readOnly = true)
    public void getLocationWithTranslation(Long locationId, String languageCode) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + locationId));
        translationRepository.findByLocationIdAndLanguageCode(locationId, languageCode).ifPresent(translation -> {
            location.setName(translation.getName());
            location.setDescription(translation.getDescription());
        });
    }
}