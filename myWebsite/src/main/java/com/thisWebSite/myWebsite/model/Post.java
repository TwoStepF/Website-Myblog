package com.thisWebSite.myWebsite.model;




import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Lob
    @Column(name = "Content",nullable = false)
    private String Content;

    @Column(name = "created_On")
    private Instant createdOn;

    @Column(name = "Update_On")
    private Instant updatedOn;

    @Column(name = "view")
    private Long view;

    @Column(name = "point")
    private Double point;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "Post_tag", joinColumns = @JoinColumn(name = "Post_id"), inverseJoinColumns = @JoinColumn(name = "Tag_id"))
    private Set<Tag> linkedTag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<Tag> getLinkedTag() {
        return linkedTag;
    }

    public void setLinkedTag(Set<Tag> linkedTag) {
        this.linkedTag = linkedTag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
}
