package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdiomRepository extends JpaRepository<Idiom, Long> {
    List<Idiom> findAll();

    Optional<Idiom> findByName(String idiom);

}
