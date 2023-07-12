package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.repositories.AboutRepository;
import com.vrj.mysite.repositories.IdiomRepository;
import com.vrj.mysite.repositories.ImageRepository;
import com.vrj.mysite.services.AboutService;
import com.vrj.mysite.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutRepository aboutRepository;
    @Autowired
    private IdiomRepository idiomRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public ResponseEntity<About> createAbout(About about) throws Exception {
        Optional<About> localAbout = this.aboutRepository.findByName(about.getName());

        if (localAbout.isPresent())
            throw new Exception("About already exists.");

        Set<Image> savedImages = new HashSet<>();
        for (Image image : about.getImages()) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        about.setImages(savedImages);

        Optional<Idiom> idiom = this.idiomRepository.findById(about.getIdiom().getId());
        if (idiom.isPresent())
            about.setIdiom(idiom.get());
        else
            about.setIdiom(this.idiomRepository.save(about.getIdiom()));

        about = this.aboutRepository.save(about);

        return ResponseEntity.ok(about);
    }

    @Override
    public ResponseEntity<String> updateAbout(Long id, About about) throws Exception {
        Optional<About> localAbout = this.aboutRepository.findById(id);

        if (localAbout.isEmpty())
            throw new Exception("Section about not found.");

        About savedAbout = localAbout.get().update(about);
        this.aboutRepository.save(savedAbout);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("About section has been update.");
    }

    @Override
    public ResponseEntity<String> deleteAbout(Long id) {
        if (this.aboutRepository.existsById(id)) {
            this.aboutRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("About section successfully removed.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("About section not found.");
    }

    @Override
    public ResponseEntity<About> getByIdiomId(Long id) throws Exception {
        Optional<About> about = this.aboutRepository.findByIdiom_id(id);

        if (about.isEmpty())
            throw new Exception("About section not found");

        return ResponseEntity.ok(about.get());
    }

    @Override
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws Exception {
        Optional<About> about = this.aboutRepository.findById(id);

        if (about.isEmpty())
            throw new Exception("About section not found.");

        Set<Image> savedImages = new HashSet<>();
        for (Image image : images) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        about.get().setImages(savedImages);

        About savedAbout = this.aboutRepository.save(about.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }
}
