package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.PostFoundException;
import com.vrj.mysite.exceptions.PostNotFoundException;
import com.vrj.mysite.model.Image;
import com.vrj.mysite.model.Post;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            return postService.createPost(post);
        } catch (PostFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id,
                                             @RequestBody Post post) {
        try {
            return postService.updatePost(id, post);
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        try {
            return postService.getById(id);
        } catch (PostFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAll() {
        return postService.getAll();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<Post>> searchPostsByTitle(@RequestParam("title") String title) {
        return postService.searchByName(title);
    }

    @GetMapping("/search/tag")
    public ResponseEntity<Set<Post>> searchPostsByTag(@RequestParam("tagName") String tagName) {
        return postService.searchByTagName(tagName);
    }

    @PutMapping("/{id}/add-images")
    public ResponseEntity<String> addImagesToPost(@PathVariable("id") Long id,
                                                  @RequestBody Set<Image> images) {
        try {
            return postService.addImages(id, images);
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/add-tags")
    public ResponseEntity<String> addTagsToPost(@PathVariable("id") Long id,
                                                @RequestBody Set<Tag> tags) {
        try {
            return postService.addTags(id, tags);
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
