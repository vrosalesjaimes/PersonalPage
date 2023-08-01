package com.vrj.mysite.controller;

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

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/scholar-projects")
@CrossOrigin("*")
public class ScholarProjectController {
    @Autowired
    private ScholarProjectService scholarProjectService;

    @PostMapping("/create")
    public ResponseEntity<ScholarProject> createScholarProject(@RequestBody ScholarProject scholarProject) {
        try {
            return scholarProjectService.creteScholarProject(scholarProject);
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

    @GetMapping("/")
    public ResponseEntity<List<ScholarProject>> getAll() {
        return scholarProjectService.getAll();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<ScholarProject>> searchScholarProjectsByTitle(@RequestParam("title") String title) {
        return scholarProjectService.getByTitle(title);
    }

    @GetMapping("/search/tag")
    public ResponseEntity<Set<ScholarProject>> searchScholarProjects(@RequestParam("tagName") String tagName) {
        return scholarProjectService.getByTagName(tagName);
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
