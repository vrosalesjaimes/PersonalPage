package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String phone;
    private boolean enable = false;
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="user_rol", joinColumns = @JoinColumn(name =  "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> rols;
}
