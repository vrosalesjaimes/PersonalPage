package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.ReferenceFoundException;
import com.vrj.mysite.exceptions.ReferenceNotFoundException;
import com.vrj.mysite.model.Author;
import com.vrj.mysite.model.Reference;
import com.vrj.mysite.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/references")
@CrossOrigin("*")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @PostMapping("/create")
    public ResponseEntity<?> createReference(@RequestBody Reference reference) {
        try {
            return this.referenceService.createReference(reference);
        } catch (ReferenceFoundException e) {
            System.out.println("Reference already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reference already exists.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReference(@PathVariable(value = "id") Long id,
                                                  @RequestBody Reference reference) {
        try {
            return this.referenceService.updateReference(id, reference);
        } catch (ReferenceNotFoundException e) {
            System.out.println("Reference not found.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reference not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReference(@PathVariable Long id) {
        return this.referenceService.deleteReference(id);
    }

    @PutMapping("/{id}/add-authors")
    public ResponseEntity<String> addAuthors(@PathVariable(value = "id") Long id,
                                             @RequestBody Set<Author> authors) {
        try {
            return this.referenceService.addAuthors(id, authors);
        } catch (ReferenceFoundException e) {
            System.out.println("Reference not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reference not found");
        }
    }

}
