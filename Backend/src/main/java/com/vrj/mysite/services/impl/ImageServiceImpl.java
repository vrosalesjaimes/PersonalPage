package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.exceptions.ImageNotFoundException;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.repositories.ImageRepository;
import com.vrj.mysite.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<Image> createImage(Image image) throws ImageFoundException {
        Optional<Image> localImage = imageRepository.findByUrl(image.getUrl());

        if(localImage.isPresent())
            throw new ImageFoundException();

        Image savedImage = imageRepository.save(image);

        return ResponseEntity.ok(savedImage);
    }

    @Override
    public ResponseEntity<String> updateImage(Long id, Image image) throws ImageNotFoundException{
        Optional<Image> localImage = imageRepository.findById(id);

        if(localImage.isEmpty())
            throw new ImageNotFoundException();

        Image updatedImage = localImage.get().upload(image);
        imageRepository.save(updatedImage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The image has been updated");
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
