package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.exceptions.ImageNotFoundException;
import com.vrj.mysite.model.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    ResponseEntity<Image> createImage(Image image) throws ImageFoundException;

    ResponseEntity<String> updateImage(Long id, Image image) throws ImageNotFoundException;

    void deleteImage(Long id);

}
