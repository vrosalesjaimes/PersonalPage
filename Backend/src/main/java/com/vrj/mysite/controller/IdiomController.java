package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.IdiomNotFoundException;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.services.IdiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/idiom")
@CrossOrigin("*")
public class IdiomController {

    @Autowired
    private IdiomService idiomService;

    @PostMapping("/create")
    private ResponseEntity<?> createIdiom(@RequestBody Idiom idiom) {
        try {
            return this.idiomService.createIdiom(idiom);
        } catch (IdiomFoundException e) {
            System.out.println("Idiom already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Idiom already exist.");
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateIdiom(@PathVariable(value = "id") Long id,
                                          @RequestBody Idiom idiom){
        try {
            return this.idiomService.updateIdiom(id,idiom);
        } catch (IdiomNotFoundException e) {
            System.out.println("Idiom not found.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Idiom not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteIdiom(@PathVariable Long id){
        return idiomService.deleteIdiom(id);
    }
}
