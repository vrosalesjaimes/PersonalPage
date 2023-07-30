package com.vrj.mysite.repositories;

import com.vrj.mysite.model.ScholarProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ScholarProjectRepository extends JpaRepository<ScholarProject, Long> {
    Optional<ScholarProject> findByTitle(String title);

    List<ScholarProject> findAll();

    Set<ScholarProject> findAllByTags_NameContainingIgnoreCase(String tagName);

    Set<ScholarProject> findAllByTitleContainingIgnoreCase(String title);
}
