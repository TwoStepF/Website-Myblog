package com.thisWebSite.myWebsite.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "NonLock", length = 1, nullable = false)
    private boolean Nonlock;

    @Column(name = "Role", length = 10, nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> Posts;

    public User() {
        this.role = "ROLE_USER";
    }

    public User(String userName, String encrytedPassword) {
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.Nonlock = true;
        this.role = "ROLE_USER";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isNonlock() {
        return Nonlock;
    }

    public void setNonlock(boolean nonlock) {
        Nonlock = nonlock;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Post> getPosts() {
        return Posts;
    }

    public void setPosts(Set<Post> posts) {
        Posts = posts;
    }
}
