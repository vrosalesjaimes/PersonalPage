package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String name;

    @NotBlank
    private String content;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Image.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "about_image", joinColumns = @JoinColumn(name = "about_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    public About update(About about) {

        if (about.getContent() != null)
            this.content = about.getContent();

        return this;
    }

    public void addImages(Set<Image> addedImages) {
        this.images.addAll(addedImages);
    }


}
