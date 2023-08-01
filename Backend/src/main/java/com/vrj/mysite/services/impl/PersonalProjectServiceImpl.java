package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.*;
import com.vrj.mysite.model.*;
import com.vrj.mysite.repositories.*;
import com.vrj.mysite.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonalProjectServiceImpl implements PersonalProjectService {

    @Autowired
    private PersonalProjectRepository personalProjectRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagService tagService;

    @Override
    public ResponseEntity<PersonalProject> createPersonalProject(PersonalProject personalProject) throws PersonalProjectFoundException {

        Optional<PersonalProject> localProject = this.personalProjectRepository
                .findByTitle(personalProject.getTitle());
        if (localProject.isPresent())
            throw new PersonalProjectFoundException();

        Set<Image> savedImages = new HashSet<>();
        for (Image image : personalProject.getImages()) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        personalProject.setImages(savedImages);

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : personalProject.getAuthors()) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }
        personalProject.setAuthors(savedAuthors);

        Set<Reference> savedReferences = new HashSet<>();
        for (Reference reference : personalProject.getReferences()) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        personalProject.setReferences(savedReferences);

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : personalProject.getTags()) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        personalProject.setTags(savedTag);

        personalProject = this.personalProjectRepository.save(personalProject);

        return ResponseEntity.ok(personalProject);
    }

    @Override
    public ResponseEntity<String> updatePersonalProject(Long id, PersonalProject personalProject) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);
        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        PersonalProject savedProject = localProject.get().update(personalProject);
        this.personalProjectRepository.save(savedProject);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Project has been update");
    }

    @Override
    public ResponseEntity<String> deletePersonalProject(Long id) {
        if (this.personalProjectRepository.existsById(id)) {
            this.personalProjectRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Project successfully removed.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }

    @Override
    public ResponseEntity<PersonalProject> getById(Long id) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);

        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        return ResponseEntity.ok(localProject.get());
    }

    @Override
    public ResponseEntity<List<PersonalProject>> getAll() {
        List<PersonalProject> projects = this.personalProjectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByTitle(String title) {
        Set<PersonalProject> projects = this.personalProjectRepository.findAllByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByTagName(String tagNAme) {
        Set<PersonalProject> projects = this.personalProjectRepository.findAllByTags_NameContainingIgnoreCase(tagNAme);
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByAuthorName(String authorName) {
        Set<PersonalProject> projects = this.personalProjectRepository.findAllByAuthors_NameContainingIgnoreCase(authorName);
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);

        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        Set<Image> savedImages = new HashSet<>();
        for (Image image : images) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        localProject.get().addImages(savedImages);
        this.personalProjectRepository.save(localProject.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }

    @Override
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);

        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : authors) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }
        localProject.get().addAuthors(savedAuthors);
        this.personalProjectRepository.save(localProject.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }

    @Override
    public ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);

        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        Set<Reference> savedReferences = new HashSet<>();
        for (Reference reference : references) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        localProject.get().addReferences(savedReferences);
        this.personalProjectRepository.save(localProject.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }

    @Override
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PersonalProjectNotFoundException {
        Optional<PersonalProject> localProject = this.personalProjectRepository.findById(id);

        if (localProject.isEmpty())
            throw new PersonalProjectNotFoundException();

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : tags) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        localProject.get().addTags(savedTag);
        this.personalProjectRepository.save(localProject.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }
}
