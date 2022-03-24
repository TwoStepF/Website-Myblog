package com.thisWebSite.myWebsite.DTO;

import com.thisWebSite.myWebsite.model.Tag;

import java.util.ArrayList;


public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String username;
    private String updateOn;
    private String roleUser;
    private Long view;
    private ArrayList<String> tag = new ArrayList<>();

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public String getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(String updateOn) {
        this.updateOn = updateOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }
}
