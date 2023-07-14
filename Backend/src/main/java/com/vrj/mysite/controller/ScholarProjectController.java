package com.vrj.mysite.controller;

import com.vrj.mysite.dto.ScholarProjectDTO;
import com.vrj.mysite.exceptions.ScholarProjectFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.model.ScholarProject;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.model.Reference;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.services.ScholarProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/scholar-projects")
@CrossOrigin("*")
public class ScholarProjectController {
    @Autowired
    private ScholarProjectService scholarProjectService;

    @PostMapping("/create")
    public ResponseEntity<ScholarProject> createScholarProject(@RequestParam("idiomId") Long idiomId,
                                                               @RequestBody ScholarProject scholarProject) {
        try {
            return scholarProjectService.creteScholarProject(idiomId, scholarProject);
        } catch (ScholarProjectFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateScholarProject(@PathVariable("id") Long id,
                                                       @RequestBody ScholarProject scholarProject) {
        try {
            return scholarProjectService.updateScholarProject(id, scholarProject);
        } catch (ScholarProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScholarProject(@PathVariable("id") Long id) {
        return scholarProjectService.deleteScholarProject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarProject> getScholarProjectById(@PathVariable("id") Long id) {
        try {
            return scholarProjectService.getById(id);
        } catch (ScholarProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/by-idiom/{idiomId}")
    public ResponseEntity<Set<ScholarProjectDTO>> getScholarProjectsByLanguage(@PathVariable("idiomId") Long idiomId) {
        return scholarProjectService.getAllByIdiom(idiomId);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<ScholarProjectDTO>> searchScholarProjectsByTitleAndIdiom(@RequestParam("title") String title,
                                                                                       @RequestParam("idiomId") Long idiomId) {
        return scholarProjectService.getByTitleAndIdiomId(title, idiomId);
    }

    @GetMapping("/search/tag")
    public ResponseEntity<Set<ScholarProjectDTO>> searchScholarProjectsByTagAndIdiom(@RequestParam("tagName") String tagName,
                                                                                     @RequestParam("idiomId") Long idiomId) {
        return scholarProjectService.getByTagNameAndIdiomId(tagName, idiomId);
    }

    @PutMapping("/{id}/add-images")
    public ResponseEntity<String> addImagesToScholarProject(@PathVariable("id") Long id,
                                                            @RequestBody Set<Image> images) {
        try {
            return scholarProjectService.addImages(id, images);
        } catch (ScholarProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-references")
    public ResponseEntity<String> addReferencesToScholarProject(@PathVariable("id") Long id,
                                                                @RequestBody Set<Reference> references) {
        try {
            return scholarProjectService.addReferences(id, references);
        } catch (ScholarProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-tags")
    public ResponseEntity<String> addTagsToScholarProject(@PathVariable("id") Long id,
                                                          @RequestBody Set<Tag> tags) {
        try {
            return scholarProjectService.addTags(id, tags);
        } catch (ScholarProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
