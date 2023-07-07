package com.vrj.mysite.services;

import com.vrj.mysite.model.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    public ResponseEntity<Image> createImage(Image image);
    public ResponseEntity<Image> updateImage(Long id, Image image);
    public void deleteImage(Long id);

}
