package com.vrj.mysite.controller;

import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.services.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/about")
@CrossOrigin("*")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @PostMapping("/create")
    public ResponseEntity<?> createAbout(@RequestBody About about){
        try {
            return this.aboutService.createAbout(about);
        } catch (Exception e) {
            System.out.println("About section already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("About section already exists.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAbout(@PathVariable(value = "id") Long id,
                                              @RequestBody About about){
        try {
            return this.aboutService.updateAbout(id, about);
        } catch (Exception e) {
            System.out.println("About not found.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("About not found.");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAbout(@PathVariable(value = "id") Long id){
        return this.aboutService.deleteAbout(id);
    }

    @PutMapping("/{id}/add-images")
    public ResponseEntity<?> addImages(@PathVariable(value = "id") Long id,
                                       @RequestBody Set<Image> images){
        try {
            return this.aboutService.addImages(id, images);
        } catch (Exception e) {
            System.out.println("About section not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("About section not found.");
        }
    }
}
