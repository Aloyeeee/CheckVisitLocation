package com.checkvisitlocation.models;

import com.checkvisitlocation.enums.TagType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TagType getType() { return type; }
    public void setType(TagType type) { this.type = type; }
}