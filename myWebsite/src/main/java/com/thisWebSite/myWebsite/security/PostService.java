package com.thisWebSite.myWebsite.security;

import com.thisWebSite.myWebsite.DTO.PostDTO;
import com.thisWebSite.myWebsite.DTO.TagDTO;
import com.thisWebSite.myWebsite.Exeption.PostNotFoundException;
import com.thisWebSite.myWebsite.Exeption.StatusP;
import com.thisWebSite.myWebsite.Service.AuthService;
import com.thisWebSite.myWebsite.model.Post;
import com.thisWebSite.myWebsite.model.Tag;
import com.thisWebSite.myWebsite.repository.PostRepository;
import com.thisWebSite.myWebsite.repository.TagRepository;
import com.thisWebSite.myWebsite.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.pow;


@Service
public class PostService {
    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private userRepository UserRepository;

    @Transactional
    public List<PostDTO> showAllPosts() {
        List<Post> posts = postRepository.findAllPost().orElseThrow();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<StatusP> createPost(PostDTO postDto) {
        if (postDto.getTag().size() != 0) {
            Post post = mapFromDtoToPost(postDto);
            postRepository.save(post);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusP("oke", "thành công"));
        } else {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new StatusP("false", "ko thành công"));
        }
    }

    @Transactional
    public ResponseEntity<StatusP> UpdatePost(PostDTO postDto) {
        Post data = postRepository.findById(postDto.getId()).orElseThrow();
        User loginUser = authService.getCurrentUser().orElseThrow();
        if (data.getUser().getUserName().equals(loginUser.getUsername())) {
            data.setTitle(postDto.getTitle());
            data.setContent(postDto.getContent());
            data.setUpdatedOn(Instant.now());
            Set<Tag> tag = new HashSet<>();
            for (String x : postDto.getTag())
                tag.add(tagRepository.findTagByTagName(x).orElseThrow());
            data.setLinkedTag(tag);
            postRepository.save(data);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusP("oke", "thành công"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusP("false", "Không thể gửi"));
    }


    @Transactional
    public PostDTO readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("không thể thực hiện"));
        return mapFromPostToDto(post);
    }

    private PostDTO mapFromPostToDto(Post post) {
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUser().getUserName());
        String[] a = post.getUpdatedOn().toString().split("T");
        postDto.setUpdateOn(a[0]);
        for (Tag x : post.getLinkedTag()) {
            postDto.getTag().add(x.getTagName());
        }
        postDto.setRoleUser(post.getUser().getRole());
        postDto.setView(post.getView());
        return postDto;
    }

    private TagDTO mapFromTagToTagDto(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTagId(tag.getTagId());
        tagDTO.setTagName(tag.getTagName());
        return tagDTO;
    }

    private Post mapFromDtoToPost(PostDTO postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        com.thisWebSite.myWebsite.model.User user = UserRepository.findByUserName(loggedInUser.getUsername()).orElseThrow();
        post.setCreatedOn(Instant.now());
        post.setUser(user);
        post.setUpdatedOn(Instant.now());
        post.setView((long) 0);
        post.setPoint(0.0);
        Set<Tag> tag = new HashSet<>();
        for (String x : postDto.getTag()) {
            try {
                tag.add(tagRepository.findTagByTagName(x).orElseThrow());
            } catch (Exception e) {
                continue;
            }
        }
        post.setLinkedTag(tag);
        return post;
    }

    @Transactional
    public List<PostDTO> showAllPostsOfMe() {
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        com.thisWebSite.myWebsite.model.User user = UserRepository.findByUserName(loggedInUser.getUsername()).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        return user.getPosts().stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    @Transactional
    public List<TagDTO> showAllTag() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(this::mapFromTagToTagDto).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDTO> showAllPostsByTag(Long tag) {
        Tag tags = tagRepository.findById(tag).orElseThrow();
        return tags.getPosts().stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDTO> searchPost(String key) {
        List<Post> posts = postRepository.searchPost(key).orElseThrow();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }


    public ResponseEntity<StatusP> deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        User user = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        com.thisWebSite.myWebsite.model.User InUser = UserRepository.findByUserName(user.getUsername()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        com.thisWebSite.myWebsite.model.User UserPost = UserRepository.findByUserName(post.getUser().getUserName()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        if (InUser.getRole().equals("ROLE_SAD") || post.getUser().getUserName().equals(InUser.getUserName()) ||
                (InUser.getRole().equals("ROLE_ADMIN") && (UserPost.getRole().equals("ROLE_USER") || UserPost.getRole().equals("ROLE_VUSER")))) {
            postRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusP("oke", "thành công"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusP("false", "ko thành công"));
    }

    public ResponseEntity<StatusP> UpdateViewPost(PostDTO postDTO) {
        Post data = postRepository.findById(postDTO.getId()).orElseThrow();
        if (postDTO.getView() - data.getView() == 1) {
            data.setView(postDTO.getView());
            postRepository.save(data);
            List<Post> posts = postRepository.findAll();
            for(Post x: posts){
                int h = postRepository.TimeNow(x.getId(), Instant.now());
                x.setPoint( x.getView()/pow((h+2), 1.8));
                System.out.println(x.getPoint());
                postRepository.save(x);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new StatusP("oke", "thành công"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusP("false", "ko thành công"));
    }

    public List<PostDTO> getTop(){
        List<Post> posts = postRepository.findAllTopPost().orElseThrow();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }
}
