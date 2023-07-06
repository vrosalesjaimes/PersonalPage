package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "about")
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;

    @OneToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Image.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="about_image", joinColumns = @JoinColumn(name =  "about_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

}
