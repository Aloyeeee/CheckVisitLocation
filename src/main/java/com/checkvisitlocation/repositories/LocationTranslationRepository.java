package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.LocationTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторій для роботи з перекладами локацій (LocationTranslation).
 */
public interface LocationTranslationRepository extends JpaRepository<LocationTranslation, Long> {
    /**
     * Повертає переклад локації за ідентифікатором локації та кодом мови.
     * @param locationId ідентифікатор локації
     * @param languageCode код мови
     * @return Optional з перекладом, якщо знайдено
     */
    Optional<LocationTranslation> findByLocationIdAndLanguageCode(Long locationId, String languageCode);
}