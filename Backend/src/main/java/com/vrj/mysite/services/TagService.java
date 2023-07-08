package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.exceptions.TagNotFoundException;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

public interface TagService {

    public ResponseEntity<?> createTag(Tag tag) throws TagFoundException;
    public ResponseEntity<String> updateTag(Long id, Tag tag) throws TagNotFoundException;
    public void deleteTag(Long id);
}
