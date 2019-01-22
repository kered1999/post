package com.cybr406.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public Page<Post> getPosts( Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @GetMapping("/posts/{id}")
    public Page<Post> getSinglePosts(@PathVariable(value = "id", required = false) long id, Pageable pageable){

        return postRepository.findAll(pageable);
    }
}
