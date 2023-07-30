package com.vrj.mysite.repositories;

import com.vrj.mysite.model.PersonalProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonalProjectRepository extends JpaRepository<PersonalProject, Long> {
    Optional<PersonalProject> findByTitle(String title);

    List<PersonalProject> findAll();

    Set<PersonalProject> findAllByAuthors_NameContainingIgnoreCase(String authorName);

    Set<PersonalProject> findAllByTags_NameContainingIgnoreCase(String tagName);

    Set<PersonalProject> findAllByTitleContainingIgnoreCase(String title);
}
