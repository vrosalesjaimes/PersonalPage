package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.IdiomNotFoundException;
import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.repositories.IdiomRepository;
import com.vrj.mysite.repositories.ImageRepository;
import com.vrj.mysite.services.IdiomService;
import com.vrj.mysite.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdiomServiceImpl implements IdiomService {

    @Autowired
    private IdiomRepository idiomRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<Idiom> createIdiom(Idiom idiom) throws IdiomFoundException {
        Optional<Idiom> localIdiom = this.idiomRepository.findByName(idiom.getName());
        Image image;

        if (localIdiom.isPresent())
            throw new IdiomFoundException();

        try {
            image = this.imageService.createImage(idiom.getImage()).getBody();
            idiom.setImage(image);
        } catch (ImageFoundException e) {
            System.out.println("The idiom image already exists.");
        }

        idiom = idiomRepository.save(idiom);

        return ResponseEntity.ok(idiom);
    }

    @Override
    public ResponseEntity<String> updateIdiom(Long id, Idiom idiom) throws IdiomNotFoundException {
        Optional<Idiom> localIdiom = this.idiomRepository.findById(id);

        if (localIdiom.isEmpty())
            throw new IdiomNotFoundException();

        this.update(localIdiom.get(), idiom);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The idiom has been updated");
    }

    @Override
    public ResponseEntity<?> deleteIdiom(Long id) {
        if (this.idiomRepository.findById(id).isPresent()) {
            idiomRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Idiom successfully removed");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idiom not found");
    }


    private void update(Idiom localIdiom, Idiom idiom) {
        Image image;

        if (idiom.getImage() != null) {
            try {
                image = this.imageService.createImage(idiom.getImage()).getBody();
                localIdiom.setImage(image);
            } catch (ImageFoundException e) {
                System.out.println("The idiom image already exists.");
            }
        }
        if (idiom.getName() != null) {
            localIdiom.setName(idiom.getName());
        }

        idiomRepository.save(localIdiom);
    }
}
