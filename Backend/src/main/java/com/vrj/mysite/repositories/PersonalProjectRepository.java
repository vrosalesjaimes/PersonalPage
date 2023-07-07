package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.PersonalProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PersonalProjectRepository extends JpaRepository<PersonalProject, Long> {

    public Optional<PersonalProject> findByTitle(String title);

    public Set<PersonalProject> findAllByIdiom(Idiom idiom);
}
