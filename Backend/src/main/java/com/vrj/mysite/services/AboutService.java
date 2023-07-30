package com.vrj.mysite.services;

import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Image;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface AboutService {

    ResponseEntity<About> createAbout(About about) throws Exception;

    ResponseEntity<String> updateAbout(Long id, About about) throws Exception;

    ResponseEntity<String> deleteAbout(Long id);

    ResponseEntity<String> addImages(Long id, Set<Image> images) throws Exception;
}
