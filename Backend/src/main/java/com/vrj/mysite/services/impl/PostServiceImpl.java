package com.vrj.mysite.services.impl;

import com.vrj.mysite.dto.PostDTO;
import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.exceptions.PostFoundException;
import com.vrj.mysite.exceptions.PostNotFoundException;
import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.model.Post;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.repositories.ImageRepository;
import com.vrj.mysite.repositories.PostRepository;
import com.vrj.mysite.repositories.TagRepository;
import com.vrj.mysite.services.ImageService;
import com.vrj.mysite.services.PostService;
import com.vrj.mysite.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagService tagService;

    @Override
    public ResponseEntity<Post> createPost(Long idiomId, Post post) throws PostFoundException {
        Optional<Idiom> idiom = this.idiomRepository.findById(idiomId);
        if (idiom.isPresent())
            post.setIdiom(idiom.get());
        else
            post.setIdiom(this.idiomRepository.save(post.getIdiom()));

        Optional<Post> localPost = this.postRepository.findByTitleAndIdiom_id(post.getTitle(), idiomId);
        if (localPost.isPresent())
            throw new PostFoundException();


        Set<Image> savedImages = new HashSet<>();
        for (Image image : post.getImages()) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }
        post.setImages(savedImages);

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : post.getTags()) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        post.setTags(savedTag);

        post = this.postRepository.save(post);

        return ResponseEntity.ok(post);
    }

    @Override
    public ResponseEntity<String> updatePost(Long id, Post post) throws PostNotFoundException {
        Optional<Post> localPost = this.postRepository.findById(id);

        if (localPost.isEmpty())
            throw new PostNotFoundException();

        Post savedPost = localPost.get().update(post);
        this.postRepository.save(savedPost);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post has been update.");
    }

    @Override
    public ResponseEntity<String> deletePost(Long id) {
        if (this.postRepository.existsById(id)) {
            this.postRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post successfully deleted.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
    }

    @Override
    public ResponseEntity<Set<PostDTO>> getAllByIdiomId(Long idiomId) {
        Set<Post> posts = this.postRepository.findAllByIdiomId(idiomId);
        return ResponseEntity.ok(this.setPostToSetPostDTO(posts));
    }

    @Override
    public ResponseEntity<Post> getById(Long id) throws PostFoundException {
        Optional<Post> post = this.postRepository.findById(id);

        if (post.isEmpty())
            throw new PostFoundException();

        return ResponseEntity.ok(post.get());
    }

    @Override
    public ResponseEntity<Set<PostDTO>> searchByNameAndIdiomId(String title, Long idiomId) {
        Set<Post> posts = this.postRepository.findAllByTitleContainingIgnoreCaseAndIdiom_Id(title, idiomId);
        return ResponseEntity.ok(this.setPostToSetPostDTO(posts));
    }

    @Override
    public ResponseEntity<Set<PostDTO>> searchByTagNameAndIdiomId(String tagName, Long idiomId) {
        Set<Post> posts = this.postRepository.findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(tagName, idiomId);
        return ResponseEntity.ok(this.setPostToSetPostDTO(posts));
    }

    @Override
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws PostNotFoundException {
        Optional<Post> localPost = this.postRepository.findById(id);

        if (localPost.isEmpty())
            throw new PostNotFoundException();

        Set<Image> savedImages = new HashSet<>();
        for (Image image : images) {
            try {
                savedImages.add(this.imageService.createImage(image).getBody());
            } catch (ImageFoundException e) {
                savedImages.add(this.imageRepository.findByUrl(image.getUrl()).get());
            }
        }

        localPost.get().addImages(savedImages);
        this.postRepository.save(localPost.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }

    @Override
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PostNotFoundException {
        Optional<Post> localPost = this.postRepository.findById(id);

        if (localPost.isEmpty())
            throw new PostNotFoundException();

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : tags) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }

        localPost.get().addTags(savedTag);
        this.postRepository.save(localPost.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Tags have been successfully added");
    }

    public Set<PostDTO> setPostToSetPostDTO(Set<Post> posts) {
        return posts.stream()
                .map(Post::toDTO)
                .collect(Collectors.toSet());
    }
}
