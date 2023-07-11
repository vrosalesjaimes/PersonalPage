package com.vrj.mysite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleDTO {
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
}
