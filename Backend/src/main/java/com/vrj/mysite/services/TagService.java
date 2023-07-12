package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.exceptions.TagNotFoundException;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

public interface TagService {

    ResponseEntity<Tag> createTag(Tag tag) throws TagFoundException;

    ResponseEntity<String> updateTag(Long id, Tag tag) throws TagNotFoundException;

    ResponseEntity<String> deleteTag(Long id);
}
