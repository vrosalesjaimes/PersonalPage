package com.vrj.mysite.repositories;

import com.vrj.mysite.model.PersonalProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PersonalProjectRepository extends JpaRepository<PersonalProject, Long> {
    Optional<PersonalProject> findByTitleAndIdiom_id(String title, Long idiom_id);

    Set<PersonalProject> findAllByIdiom_id(Long idiomId);

    Set<PersonalProject> findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(String authorName, Long idiomId);

    Set<PersonalProject> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);

    Set<PersonalProject> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
}
