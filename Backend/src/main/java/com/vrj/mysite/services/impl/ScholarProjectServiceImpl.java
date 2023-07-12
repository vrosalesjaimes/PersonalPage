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

public class ScholarProjectServiceImpl implements ScholarProjectService {
    
    @Autowired
    private ScholarProjectRepository scholarProjectRepository;
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
    public ResponseEntity<ScholarProject> creteScholarProject(Long idiomId, ScholarProject scholarProject) throws ScholarProjectFoundException {
        Optional<Idiom> idiom = this.idiomRepository.findById(idiomId);
        if (idiom.isPresent())
            scholarProject.setIdiom(idiom.get());
        else
            scholarProject.setIdiom(this.idiomRepository.save(scholarProject.getIdiom()));

        Optional<ScholarProject> localProject = this.scholarProjectRepository
                .findByTitleAndIdiom_id(scholarProject.getTitle(), idiomId);
        if(localProject.isPresent())
            throw new ScholarProjectFoundException();


        Set<Image> savedImages = new HashSet<>();
        for (Image image : scholarProject.getImages()) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        scholarProject.setImages(savedImages);

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : scholarProject.getAuthors()) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }
        scholarProject.setAuthors(savedAuthors);

        Set<Reference> savedReferences = new HashSet<>();
        for (Reference reference: scholarProject.getReferences()) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        scholarProject.setReferences(savedReferences);

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag: scholarProject.getTags()){
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        scholarProject.setTags(savedTag);

        scholarProject = this.scholarProjectRepository.save(scholarProject);

        return ResponseEntity.ok(scholarProject);
    }

    @Override
    public ResponseEntity<String> updateScholarProject(Long id, ScholarProject scholarProject) throws ScholarProjectNotFoundException {
        Optional<ScholarProject> localProject = this.scholarProjectRepository.findById(id);
        if (localProject.isEmpty())
            throw new ScholarProjectNotFoundException();

        ScholarProject savedProject = this.scholarProjectRepository.save(localProject.get());
        this.scholarProjectRepository.save(savedProject);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Project has been update");
    }

    @Override
    public ResponseEntity<String> deleteScholarProject(Long id) {
        if (this.scholarProjectRepository.existsById(id)) {
            this.scholarProjectRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Project successfully removed.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }

    @Override
    public ResponseEntity<ScholarProject> getById(Long id) throws ScholarProjectNotFoundException {
        Optional<ScholarProject> localProject = this.scholarProjectRepository.findById(id);
        
        if(localProject.isEmpty())
            throw new ScholarProjectNotFoundException();
        
        return ResponseEntity.ok(localProject.get());
    }

    @Override
    public ResponseEntity<Set<ScholarProject>> getAllByIdiom(Long idiom_id) {
        return ResponseEntity.ok(this.scholarProjectRepository.findAllByIdiom_id(idiom_id));
    }

    @Override
    public ResponseEntity<Set<ScholarProject>> getByTitleAndIdiomId(String title, Long idiomId) {
        return ResponseEntity.ok(this.scholarProjectRepository.findAllByTitleContainingIgnoreCaseAndIdiom_Id(title, idiomId));

    }

    @Override
    public ResponseEntity<Set<ScholarProject>> getByTagNameAndIdiomId(String tagNAme, Long idiomId) {
        return ResponseEntity.ok(this.scholarProjectRepository.findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(tagNAme, idiomId));

    }
}
