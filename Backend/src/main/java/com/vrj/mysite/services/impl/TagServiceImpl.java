package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.exceptions.TagNotFoundException;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.repositories.IdiomRepository;
import com.vrj.mysite.repositories.TagRepository;
import com.vrj.mysite.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private IdiomRepository idiomRepository;

    @Override
    public ResponseEntity<Tag> createTag(Tag tag) throws TagFoundException {
        Optional<Tag> localTag = this.tagRepository.findByName(tag.getName());

        if (localTag.isPresent())
            throw new TagFoundException();

        tag = tagRepository.save(tag);

        return ResponseEntity.ok(tag);
    }

    @Override
    public ResponseEntity<String> updateTag(Long id, Tag tag) throws TagNotFoundException {
        Optional<Tag> localTag = this.tagRepository.findById(id);

        if (localTag.isEmpty())
            throw new TagNotFoundException();

        Tag updatedtag = localTag.get().update(tag);
        this.tagRepository.save(updatedtag);

        return ResponseEntity.status(HttpStatus.OK).body("The tag has been successfully updated");
    }

    @Override
    public ResponseEntity<String> deleteTag(Long id) {
        if (this.tagRepository.findById(id).isPresent()) {
            this.tagRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Tag successfully removed");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
    }
}
