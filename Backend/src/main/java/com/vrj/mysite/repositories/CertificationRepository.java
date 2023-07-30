package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findAll();

    Optional<Certification> findByTitle(String title);

    Set<Certification> findAllByTitleContainingIgnoreCase(String title);

    Set<Certification> findAllByTags_NameContainingIgnoreCase(String tagName);
}
