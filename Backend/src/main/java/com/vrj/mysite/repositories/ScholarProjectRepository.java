package com.vrj.mysite.repositories;

import com.vrj.mysite.model.ScholarProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ScholarProjectRepository extends JpaRepository<ScholarProject, Long> {
    Optional<ScholarProject> findByTitleAndIdiom_id(String title, Long idiom_id);

    Set<ScholarProject> findAllByIdiom_id(Long idiomId);

    Set<ScholarProject> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);

    Set<ScholarProject> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
}
