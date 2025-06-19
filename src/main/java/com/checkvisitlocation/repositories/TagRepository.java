package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторій для роботи з тегами (Tag).
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * Повертає тег за типом.
     * @param type тип тегу
     * @return Optional з тегом, якщо знайдено
     */
    Optional<Tag> findByType(TagType type);
}