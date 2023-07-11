package com.vrj.mysite.model;

import com.vrj.mysite.dto.ArticleDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String initialImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @URL
    private String link;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Image.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="article_image", joinColumns = @JoinColumn(name =  "article_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Reference.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="article_reference", joinColumns = @JoinColumn(name =  "article_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
    private Set<Reference> references;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="article_tag", joinColumns = @JoinColumn(name =  "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="article_author", joinColumns = @JoinColumn(name =  "article_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public Article update(Article article) {
        if (article.getTitle() != null) {
            this.setTitle(article.getTitle());
        }
        if (article.getDescription() != null) {
            this.setDescription(article.getDescription());
        }
        if (article.getInitialImage() != null) {
            this.setInitialImage(article.getInitialImage());
        }
        if (article.getDate() != null) {
            this.setDate(article.getDate());
        }
        if (article.getLink() != null) {
            this.setLink(article.getLink());
        }
        if (article.getContent() != null) {
            this.setContent(article.getContent());
        }
        return this;
    }

    public void addAuthors(Set<Author> addedAuthors) {
        this.authors.addAll(addedAuthors);
    }

    public void addImages(Set<Image> addedImages) {
        this.images.addAll(addedImages);
    }

    public void addReferences(Set<Reference> addedReferences) {
        this.references.addAll(addedReferences);
    }

    public void addTags(Set<Tag> addedTags) {
        this.tags.addAll(addedTags);
    }

    public ArticleDTO toDTO() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(this.id);
        articleDTO.setTitle(this.title);
        articleDTO.setDescription(this.description);
        articleDTO.setInitialImage(this.initialImage);
        articleDTO.setDate(this.date);
        articleDTO.setLink(this.link);
        return articleDTO;
    }

    public Set<ArticleDTO> setToDTOSet(Set<Article> articles){
        return  articles.stream()
                .map(Article::toDTO)
                .collect(Collectors.toSet());
    }
}
