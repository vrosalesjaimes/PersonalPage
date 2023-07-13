package com.vrj.mysite.services.impl;

import com.vrj.mysite.dto.ArticleDTO;
import com.vrj.mysite.exceptions.*;
import com.vrj.mysite.model.*;
import com.vrj.mysite.repositories.*;
import com.vrj.mysite.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
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
    public ResponseEntity<Article> createArticle(Article article) throws ArticleFoundException {
        Optional<Article> localArticle = this.articleRepository.findByTitle(article.getTitle());

        if (localArticle.isPresent())
            throw new ArticleFoundException();

        Set<Image> savedImages = new HashSet<>();
        for (Image image : article.getImages()) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        article.setImages(savedImages);

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : article.getAuthors()) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }
        article.setAuthors(savedAuthors);

        Set<Reference> savedReferences = new HashSet<>();
        for (Reference reference : article.getReferences()) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        article.setReferences(savedReferences);

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : article.getTags()) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        article.setTags(savedTag);

        Optional<Idiom> idiom = this.idiomRepository.findById(article.getIdiom().getId());
        if (idiom.isPresent())
            article.setIdiom(idiom.get());
        else
            article.setIdiom(this.idiomRepository.save(article.getIdiom()));

        article = this.articleRepository.save(article);
        return ResponseEntity.ok(article);
    }

    @Override
    public ResponseEntity<String> updateArticle(Long id, Article article) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        Article savedArticle = localArticle.get().update(article);
        this.articleRepository.save(savedArticle);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The article has been update.");
    }

    @Override
    public ResponseEntity<String> deleteArticle(Long id) {
        if (this.articleRepository.existsById(id)) {
            this.articleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Article successfully removed");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
    }

    @Override
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        Set<Image> savedImages = new HashSet<>();
        for (Image image : images) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        localArticle.get().addImages(savedImages);
        Article savedArticle = this.articleRepository.save(localArticle.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }

    @Override
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : authors) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }
        localArticle.get().addAuthors(savedAuthors);
        Article savedArticle = this.articleRepository.save(localArticle.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Authors have been successfully added");
    }

    @Override
    public ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        Set<Reference> savedReferences = new HashSet<>();
        for (Reference reference : references) {
            try {
                savedReferences.add(this.referenceService.createReference(reference).getBody());
            } catch (ReferenceFoundException e) {
                savedReferences.add(this.referenceRepository.findByName(reference.getName()).get());
            }
        }
        localArticle.get().addReferences(savedReferences);
        Article article = this.articleRepository.save(localArticle.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("References have been successfully added.");
    }

    @Override
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : tags) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        localArticle.get().addTags(savedTag);
        Article savedArticle = this.articleRepository.save(localArticle.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Tags have been successfully added");
    }

    @Override
    public ResponseEntity<Set<ArticleDTO>> getAllForCards(Long idiomId) {
        Set<Article> localArticles = this.articleRepository.findAllByIdiom_id(idiomId);
        return ResponseEntity.ok(this.setArticleToSetArticleDto(localArticles));
    }

    @Override
    public ResponseEntity<Article> getArticle(Long id) throws ArticleNotFoundException {
        Optional<Article> localArticle = this.articleRepository.findById(id);

        if (localArticle.isEmpty())
            throw new ArticleNotFoundException();

        return ResponseEntity.ok(localArticle.get());
    }

    @Override
    public ResponseEntity<Set<ArticleDTO>> searchByTitleAndIdiom(String title, Long idiomId) {
        Set<Article> localArticles = this.articleRepository.findAllByTitleContainingIgnoreCaseAndIdiom_Id(title, idiomId);
        return ResponseEntity.ok(this.setArticleToSetArticleDto(localArticles));
    }

    @Override
    public ResponseEntity<Set<ArticleDTO>> searchByNameAuthorAndIdIdiom(String authorName, Long idIdiom) {
        Set<Article> articles = this.articleRepository.findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(authorName, idIdiom);
        return ResponseEntity.ok(this.setArticleToSetArticleDto(articles));
    }

    @Override
    public ResponseEntity<Set<ArticleDTO>> searchByTagNameAndIdiomId(String tagName, Long id) {
        Set<Article> articles = this.articleRepository.findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(tagName, id);
        return ResponseEntity.ok(this.setArticleToSetArticleDto(articles));
    }

    public Set<ArticleDTO> setArticleToSetArticleDto(Set<Article> articles) {
        return articles.stream()
                .map(Article::toDTO)
                .collect(Collectors.toSet());
    }
}
