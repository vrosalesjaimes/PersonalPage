package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    public Optional<Reference> findByName(String name);

}
