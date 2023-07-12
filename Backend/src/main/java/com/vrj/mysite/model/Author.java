package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @URL
    private String url;
    @Email
    private String email;

    public Author update(Author author) {

        if (author.getName() != null)
            this.name = author.getName();
        if (author.getTitle() != null)
            this.title = author.getTitle();
        if (author.getUrl() != null)
            this.url = author.getUrl();
        if (author.getEmail() != null)
            this.email = author.getEmail();

        return this;
    }
}
