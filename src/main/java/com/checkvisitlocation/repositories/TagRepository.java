package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторій для роботи з тегами.
 * Надає методи для пошуку та управління тегами в базі даних.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * Знаходить тег за його типом.
     * 
     * @param type тип тегу
     * @return Optional, що містить тег, якщо він знайдений
     */
    Optional<Tag> findByType(TagType type);
}