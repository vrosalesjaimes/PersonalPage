package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Rol;
import com.vrj.mysite.model.RolApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Rol findByName(RolApplication name);
}
