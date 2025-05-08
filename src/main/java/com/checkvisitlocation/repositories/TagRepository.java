package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByType(TagType type);
}