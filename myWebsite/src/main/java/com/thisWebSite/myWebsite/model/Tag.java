package com.thisWebSite.myWebsite.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tag_Id", nullable = false)
    private Long TagId;

    @Column(name = "Tag_name", length = 36, nullable = false)
    private String TagName;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "Post_tag", joinColumns = @JoinColumn(name = "Tag_id"), inverseJoinColumns = @JoinColumn(name = "Post_id"))
    private Set<Post> Posts;

    public Tag(String tagName) {
        TagName = tagName;
    }

    public Tag() {

    }

    public Long getTagId() {
        return TagId;
    }

    public void setTagId(Long tagId) {
        TagId = tagId;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public Set<Post> getPosts() {
        return Posts;
    }

    public void setPosts(Set<Post> posts) {
        Posts = posts;
    }
}
