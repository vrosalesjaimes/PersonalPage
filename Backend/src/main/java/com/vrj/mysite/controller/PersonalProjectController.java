package com.vrj.mysite.controller;

import com.vrj.mysite.dto.PersonalProjectDTO;
import com.vrj.mysite.exceptions.*;
import com.vrj.mysite.model.*;
import com.vrj.mysite.services.PersonalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/personal-projects")
@CrossOrigin("*")
public class PersonalProjectController {
    @Autowired
    private PersonalProjectService personalProjectService;

    @PostMapping("/create")
    public ResponseEntity<PersonalProject> createPersonalProject(@RequestParam("idiomId") Long idiomId,
                                                                 @RequestBody PersonalProject personalProject) {
        try {
            return personalProjectService.createPersonalProject(idiomId, personalProject);
        } catch (PersonalProjectFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePersonalProject(@PathVariable("id") Long id,
                                                        @RequestBody PersonalProject personalProject) {
        try {
            return personalProjectService.updatePersonalProject(id, personalProject);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersonalProject(@PathVariable("id") Long id) {
        return personalProjectService.deletePersonalProject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalProject> getPersonalProjectById(@PathVariable("id") Long id) {
        try {
            return personalProjectService.getById(id);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/by-idiom/{idiomId}")
    public ResponseEntity<Set<PersonalProjectDTO>> getPersonalProjectsByLanguage(@PathVariable("idiomId") Long idiomId) {
        return personalProjectService.getAllByIdiom(idiomId);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<PersonalProjectDTO>> searchPersonalProjectsByTitleAndIdiom(@RequestParam("title") String title,
                                                                                         @RequestParam("idiomId") Long idiomId) {
        return personalProjectService.getByTitleAndIdiomId(title, idiomId);
    }

    @GetMapping("/search/tag")
    public ResponseEntity<Set<PersonalProjectDTO>> searchPersonalProjectsByTagAndIdiom(@RequestParam("tagName") String tagName,
                                                                                       @RequestParam("idiomId") Long idiomId) {
        return personalProjectService.getByTagNameAndIdiomId(tagName, idiomId);
    }

    @GetMapping("/search/author")
    public ResponseEntity<Set<PersonalProjectDTO>> searchPersonalProjectsByAuthorAndIdiom(@RequestParam("authorName") String authorName,
                                                                                          @RequestParam("idiomId") Long idiomId) {
        return personalProjectService.getByAuthorNameAndIdiomId(authorName, idiomId);
    }

    @PutMapping("/{id}/add-images")
    public ResponseEntity<String> addImagesToPersonalProject(@PathVariable("id") Long id,
                                                             @RequestBody Set<Image> images) {
        try {
            return personalProjectService.addImages(id, images);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-authors")
    public ResponseEntity<String> addAuthorsToPersonalProject(@PathVariable("id") Long id,
                                                              @RequestBody Set<Author> authors) {
        try {
            return personalProjectService.addAuthors(id, authors);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-references")
    public ResponseEntity<String> addReferencesToPersonalProject(@PathVariable("id") Long id,
                                                                 @RequestBody Set<Reference> references) {
        try {
            return personalProjectService.addReferences(id, references);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-tags")
    public ResponseEntity<String> addTagsToPersonalProject(@PathVariable("id") Long id,
                                                           @RequestBody Set<Tag> tags) {
        try {
            return personalProjectService.addTags(id, tags);
        } catch (PersonalProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
