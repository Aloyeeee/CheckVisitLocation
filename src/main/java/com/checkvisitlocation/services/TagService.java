package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервіс для роботи з тегами локацій.
 */
@Service
public class TagService {
    private final TagRepository tagRepository;

    /**
     * Конструктор сервісу TagService.
     * @param tagRepository репозиторій тегів
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Повертає існуючий тег за типом або створює новий, якщо такого немає.
     * @param type тип тегу
     * @return знайдений або створений тег
     */
    @Transactional
    public Tag getOrCreateTag(TagType type) {
        Optional<Tag> existingTag = tagRepository.findByType(type);
        if (existingTag.isPresent()) {
            return existingTag.get();
        }

        Tag tag = new Tag();
        tag.setType(type);
        return tagRepository.save(tag);
    }
}