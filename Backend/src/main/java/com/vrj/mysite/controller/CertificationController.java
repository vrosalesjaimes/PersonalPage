package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.CertificationFoundException;
import com.vrj.mysite.exceptions.CertificationNotFoundException;
import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.services.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/certifications")
@CrossOrigin("*")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Certification certification){
        try {
            return this.certificationService.createCertification(certification);
        } catch (CertificationFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Certification already exists.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") Long id,
                                         @RequestBody Certification certification){
        try {
            return this.certificationService.updateCertification(id, certification);
        } catch (CertificationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certification not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return this.certificationService.delete(id);
    }

    @GetMapping("/all-by-idiom")
    public ResponseEntity<List<Certification>> getAll(){
        return this.certificationService.getAll();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<Certification>> searchByTitle(@RequestParam("title") String title){
        return this.certificationService.searchByTitle(title);
    }

    @GetMapping("search/tagName")
    public ResponseEntity<Set<Certification>> searchByTagName(@RequestParam("tagName") String tagName){
        return this.certificationService.searchByTag(tagName);
    }

    @PutMapping("/{id}/add-tags")
    public ResponseEntity<String> addTags(@PathVariable(value = "id") Long id,
                                          @RequestBody Set<Tag> tags) {
        try {
            return this.certificationService.addTags(id, tags);
        } catch (CertificationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certification not found.");
        }
    }
}
