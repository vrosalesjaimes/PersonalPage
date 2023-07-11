package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.*;
import com.vrj.mysite.model.*;
import com.vrj.mysite.repositories.*;
import com.vrj.mysite.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PersonalProjectServiceImpl implements PersonalProjectService {

    @Autowired
    private PersonalProjectRepository personalProjectRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private IdiomRepository idiomRepository;
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
    public ResponseEntity<PersonalProject> createPersonalProject(Long idiomId,
                                                                 PersonalProject personalProject) throws PersonalProjectFoundException {
        
        Optional<PersonalProject> localProject = this.personalProjectRepository
                .findByTitleAndIdiom_id(personalProject.getTitle(), idiomId);
        if(localProject.isPresent())
            throw new PersonalProjectFoundException();

        Optional<Idiom> idiom = this.idiomRepository.findById(idiomId);
        if (idiom.isPresent())
            personalProject.setIdiom(idiom.get());
        else
            personalProject.setIdiom(this.idiomRepository.save(personalProject.getIdiom()));

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
        for (Reference reference: personalProject.getReferences()) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        personalProject.setReferences(savedReferences);

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag: personalProject.getTags()){
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
        return ResponseEntity.ok(this.personalProjectRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getAllByIdiom(Long idiom_id) {
        return ResponseEntity.ok(this.personalProjectRepository.findAllByIdiom_id(idiom_id));
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByTitleAndIdiomId(String title, Long idiomId) {
        return ResponseEntity.ok(this.personalProjectRepository.findAllByTitleContainingIgnoreCaseAndIdiom_Id(title, idiomId));
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByTagNameAndIdiomId(String tagNAme, Long idiomId) {
        return ResponseEntity.ok(this.personalProjectRepository.findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(tagNAme, idiomId));
    }

    @Override
    public ResponseEntity<Set<PersonalProject>> getByAuthorNameAndIdiomId(String authorName, Long idiomId) {
        return ResponseEntity.ok(this.personalProjectRepository.findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(authorName, idiomId));
    }
}
