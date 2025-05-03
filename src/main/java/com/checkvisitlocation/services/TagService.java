package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Tag;
import com.checkvisitlocation.models.TagType;
import com.checkvisitlocation.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

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