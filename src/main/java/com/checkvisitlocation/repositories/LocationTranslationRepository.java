package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.LocationTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationTranslationRepository extends JpaRepository<LocationTranslation, Long> {
    Optional<LocationTranslation> findByLocationIdAndLanguageCode(Long locationId, String languageCode);
}