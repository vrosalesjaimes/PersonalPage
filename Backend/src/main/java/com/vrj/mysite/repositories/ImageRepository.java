package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUrl(String url);
}
