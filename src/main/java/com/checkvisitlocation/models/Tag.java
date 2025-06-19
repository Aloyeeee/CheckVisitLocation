package com.checkvisitlocation.models;

import com.checkvisitlocation.enums.TagType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Сутність, що представляє тег для локацій.
 * Теги використовуються для категорізації та опису характеристик локацій.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "tags")
public class Tag {
    /**
     * Унікальний ідентифікатор тегу.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип тегу.
     * Не може бути null і повинен бути унікальним.
     */
    @Column(nullable = false, unique = true)
    @NotNull(message = "Tag type cannot be null")
    @Enumerated(EnumType.STRING)
    private TagType type;

    /**
     * Отримує ідентифікатор тегу.
     * 
     * @return ідентифікатор тегу
     */
    public Long getId() { return id; }

    /**
     * Встановлює ідентифікатор тегу.
     * 
     * @param id ідентифікатор тегу
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Отримує тип тегу.
     * 
     * @return тип тегу
     */
    public TagType getType() { return type; }

    /**
     * Встановлює тип тегу.
     * 
     * @param type тип тегу
     */
    public void setType(TagType type) { this.type = type; }
}