package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<?> createImage(@RequestBody Image image) {
        try {
            return this.imageService.createImage(image);
        } catch (ImageFoundException e) {
            System.out.println("Image already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Image already exist.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.OK).body("The image has been successfully removed.");
    }


}
