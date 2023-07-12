package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.exceptions.TagNotFoundException;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@CrossOrigin("*")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<?> createTag(@RequestBody Tag tag) {
        try {
            return this.tagService.createTag(tag);
        } catch (TagFoundException e) {
            System.out.println("Tag already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tag already exist.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTag(@PathVariable(value = "id") Long id,
                                       @RequestBody Tag tag) {
        try {
            return this.tagService.updateTag(id, tag);
        } catch (TagNotFoundException e) {
            System.out.println("Tag not found.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tag not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(value = "id") Long id) {
        return this.tagService.deleteTag(id);
    }

}
