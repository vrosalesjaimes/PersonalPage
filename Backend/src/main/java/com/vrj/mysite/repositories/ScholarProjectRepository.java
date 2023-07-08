package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.ScholarProject;
import com.vrj.mysite.model.ScholarProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ScholarProjectRepository extends JpaRepository<ScholarProject, Long> {

    public Optional<ScholarProject> findByTitle(String title);
    public Set<ScholarProject> findAllByIdiom(Idiom idiom);
    public Set<ScholarProject> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
    public Set<ScholarProject> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
}
