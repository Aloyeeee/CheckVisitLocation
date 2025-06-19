package com.checkvisitlocation.models;

import com.checkvisitlocation.enums.TagType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Сутність "Тег" (Tag) для категоризації локацій.
 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Tag type cannot be null")
    @Enumerated(EnumType.STRING)
    private TagType type;

    /** Ідентифікатор тегу. */
    public Long getId() { return id; }
    /**
     * Встановлює ідентифікатор тегу.
     */
    public void setId(Long id) { this.id = id; }
    /**
     * Повертає тип тегу.
     */
    public TagType getType() { return type; }
    /**
     * Встановлює тип тегу.
     */
    public void setType(TagType type) { this.type = type; }
}