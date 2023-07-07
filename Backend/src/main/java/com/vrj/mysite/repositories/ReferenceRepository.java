package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
