package com.vrj.mysite.dto;

import com.vrj.mysite.model.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
public class ScholarProjectDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @URL
    @NotBlank
    private String initialImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @URL
    @NotBlank
    private String repository;

    @NotBlank
    private String content;

    private Set<Tag> tags;
}
