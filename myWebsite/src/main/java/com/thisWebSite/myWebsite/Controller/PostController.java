package com.thisWebSite.myWebsite.Controller;

import com.thisWebSite.myWebsite.DTO.PostDTO;
import com.thisWebSite.myWebsite.DTO.TagDTO;
import com.thisWebSite.myWebsite.Exeption.StatusP;
import com.thisWebSite.myWebsite.model.Tag;
import com.thisWebSite.myWebsite.security.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<StatusP> createPost(@RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<List<PostDTO>> showAllPostsByTag(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.showAllPostsByTag(id), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDTO> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<StatusP> updatePost(@RequestBody PostDTO postDTO) {
        return postService.UpdatePost(postDTO);
    }

    @PutMapping("/view")
    public ResponseEntity<StatusP> updateViewPost(@RequestBody PostDTO postDTO) {
        System.out.println("dd");
        return postService.UpdateViewPost(postDTO);
    }
    @GetMapping("/me")
    public ResponseEntity<List<PostDTO>> showAllPostsOfMe() {
        return new ResponseEntity<>(postService.showAllPostsOfMe(), HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<TagDTO>> showAllTag() {
        return new ResponseEntity<>(postService.showAllTag(), HttpStatus.OK);
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable @RequestBody String key) {
        return new ResponseEntity<>(postService.searchPost(key), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<List<PostDTO>> TopPost() {
        return new ResponseEntity<>(postService.getTop(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StatusP> deletePost(@PathVariable @RequestBody Long id) {
        return postService.deletePost(id);
    }
}
