package com.vrj.mysite.services;

import com.vrj.mysite.model.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    public ResponseEntity<?> createImage(Image image);
    public ResponseEntity<String> updateImage(Long id, Image image);
    public void deleteImage(Long id);

}
