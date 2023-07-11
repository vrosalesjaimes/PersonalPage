package com.vrj.mysite.services;

import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Image;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface AboutService {

    public ResponseEntity<About> createAbout(About about) throws Exception;
    public ResponseEntity<String> updateAbout(Long id, About about) throws Exception;
    public ResponseEntity<String> deleteAbout(Long id);
    public ResponseEntity<About> getByIdiomId(Long id) throws Exception;
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws  Exception;
}
