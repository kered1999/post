package com.cybr406.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.CREATED);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new PostValidator());
    }

    @GetMapping("/posts")
    public Page<Post> getPosts( Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getSinglePosts(@PathVariable Long id){

        Optional<Post> result = postRepository.findById(id);
        if(result.isPresent()){
            return new ResponseEntity<Post>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
