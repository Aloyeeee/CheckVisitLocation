package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.LocationWithVisitCountResponse;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.models.LocationTranslation;
import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.repositories.LocationRepository;
import com.checkvisitlocation.repositories.LocationTranslationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервіс для роботи з локаціями, їх фільтрацією, підрахунком відвідувань та локалізацією.
 */
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationTranslationRepository translationRepository;

    /**
     * Конструктор сервісу LocationService.
     * @param locationRepository репозиторій локацій
     * @param translationRepository репозиторій перекладів локацій
     */
    public LocationService(LocationRepository locationRepository, LocationTranslationRepository translationRepository) {
        this.locationRepository = locationRepository;
        this.translationRepository = translationRepository;
    }

    /**
     * Повертає список локацій з кількістю відвідувань, відфільтрованих за тегами.
     * @param tags список типів тегів
     * @return список DTO локацій з кількістю відвідувань
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
     * Повертає список локацій з кількістю відвідувань, відфільтрованих за типами локацій.
     * @param types список типів локацій
     * @return список DTO локацій з кількістю відвідувань
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
     * Оновлює назву та опис локації відповідно до перекладу, якщо він існує.
     * @param locationId ідентифікатор локації
     * @param languageCode код мови (наприклад, "uk", "en")
     * @throws IllegalArgumentException якщо локацію не знайдено
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