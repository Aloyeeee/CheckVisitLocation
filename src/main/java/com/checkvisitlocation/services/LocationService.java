package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.models.LocationTranslation;
import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.repositories.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final TagService tagService;

    public LocationService(LocationRepository locationRepository, TagService tagService) {
        this.locationRepository = locationRepository;
        this.tagService = tagService;
    }

    @Transactional
    public Location addTagsToLocation(Long locationId, List<TagType> tagTypes) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + locationId));

        Set<Tag> tags = tagTypes.stream()
                .map(tagService::getOrCreateTag)
                .collect(Collectors.toSet());
        location.getTags().addAll(tags);

        return locationRepository.save(location);
    }

    @Transactional
    public Location removeTagsFromLocation(Long locationId, List<TagType> tagTypes) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + locationId));

        Set<Tag> tagsToRemove = tagTypes.stream()
                .map(tagService::getOrCreateTag)
                .collect(Collectors.toSet());
        location.getTags().removeAll(tagsToRemove);

        return locationRepository.save(location);
    }

    public List<Location> findLocationsByTags(List<TagType> tagTypes) {
        return locationRepository.findByTagTypes(tagTypes);
    }

    @Transactional
    public Location addTranslation(Long locationId, LocationTranslation translation) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + locationId));

        translation.setLocation(location);
        location.getTranslations().add(translation);

        return locationRepository.save(location);
    }

    public Location getLocationWithTranslation(Long locationId, String languageCode) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + locationId));

        location.getTranslations().stream()
                .filter(t -> t.getLanguageCode().equals(languageCode))
                .findFirst()
                .ifPresent(translation -> {
                    location.setName(translation.getName());
                    location.setDescription(translation.getDescription());
                });

        return location;
    }
}