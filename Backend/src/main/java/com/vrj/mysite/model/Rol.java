package com.vrj.mysite.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolApplication rol;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="user_rol", joinColumns = @JoinColumn(name =  "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;
}
