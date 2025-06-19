package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.LocationTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторій для роботи з перекладами локацій.
 * Надає методи для пошуку та управління перекладами в базі даних.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface LocationTranslationRepository extends JpaRepository<LocationTranslation, Long> {
    /**
     * Знаходить переклад локації за ідентифікатором локації та кодом мови.
     * 
     * @param locationId ідентифікатор локації
     * @param languageCode код мови
     * @return Optional, що містить переклад, якщо він знайдений
     */
    Optional<LocationTranslation> findByLocationIdAndLanguageCode(Long locationId, String languageCode);
}