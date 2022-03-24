package com.thisWebSite.myWebsite.DTO;

public class UserDTO {
    private Long userId;
    private String username;
    private boolean Nonlock;
    private String role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
