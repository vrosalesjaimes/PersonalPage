package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdiomRepository extends JpaRepository<Idiom, Long> {

    public List<Idiom> findAll();

    public Optional<Idiom> findByIdiom(String idiom);

}
