package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String initImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @NotBlank
    private String content;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Image.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "post_image", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Post update(Post post) {
        if (post.getTitle() != null)
            this.title = post.getTitle();
        if (post.getDescription() != null)
            this.description = post.getDescription();
        if (post.getInitImage() != null)
            this.initImage = post.getInitImage();
        if (post.getDate() != null)
            this.date = post.getDate();
        return this;
    }

    public void addImages(Set<Image> savedImages) {
        this.images.addAll(savedImages);
    }

    public void addTags(Set<Tag> savedTags) {
        this.tags.addAll(savedTags);
    }

}
