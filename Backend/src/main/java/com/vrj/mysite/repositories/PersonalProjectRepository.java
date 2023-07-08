package com.vrj.mysite.repositories;

import com.vrj.mysite.model.PersonalProject;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PersonalProjectRepository extends JpaRepository<PersonalProject, Long> {

    public Optional<PersonalProject> findByTitleAndIdiom_id(String title, Long idiom_id);
    public Set<PersonalProject> findAllByIdiom(Idiom idiom);
    public Set<PersonalProject> findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(String authorName, Long idiomId);
    public Set<PersonalProject> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
    public Set<PersonalProject> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
}
