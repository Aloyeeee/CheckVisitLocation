package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import com.checkvisitlocation.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервіс для роботи з тегами.
 * Надає методи для створення та отримання тегів.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Service
public class TagService {
    private final TagRepository tagRepository;

    /**
     * Конструктор для ініціалізації сервісу.
     * 
     * @param tagRepository репозиторій для роботи з тегами
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Отримує існуючий тег або створює новий, якщо тег з вказаним типом не існує.
     * Метод виконується в транзакції.
     * 
     * @param type тип тегу
     * @return існуючий або новий тег
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